package ru.job4j.nonblocking;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class NonBlockingCacheBaseTest {

    @Test
    public void when100ThreadsUpdateThenVersionIs100() throws InterruptedException {
        Base model = new Base(123, "Dmitriy");
        NonBlockingCacheBase cache = new NonBlockingCacheBase();
        cache.add(model);
        ExecutorService ex = Executors.newFixedThreadPool(100);
        for (int i = 0; i != 100; i++) {
            ex.execute(
                    () -> cache.update(new Base(123, "Eugene")));
        }
        ex.shutdown();
        ex.awaitTermination(1, TimeUnit.MINUTES);
        assertThat(cache.get(123).getVersion().get(), is(100));
        assertThat(cache.get(123).getName(), is("Eugene"));
    }

    @Test
    public void whenThen() throws InterruptedException {
        Base model = new Base(123, "Dmitriy");
        NonBlockingCacheBase cache = new NonBlockingCacheBase();
        AtomicReference<Exception> ex = new AtomicReference<>();
        cache.add(model);
        Thread first = new Thread(
                () -> {
                    try {
                        //переключаем контекст на второй Thread
                        Thread.sleep(50);

                        //теперь второй Thread считал версию и
                        //сам уснул в методе update, просыпаемся и
                        //обновляем модель напрямую
                        model.getVersion().set(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        Thread second = new Thread(
                () -> {
                    try {
                        cache.update(new Base(123, "Eugene"));
                    } catch (OptimisticException e) {
                        ex.set(e);
                    }
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(ex.get().getMessage(), is("OptimisticException"));
    }
}