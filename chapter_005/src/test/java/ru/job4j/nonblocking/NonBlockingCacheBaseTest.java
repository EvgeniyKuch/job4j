package ru.job4j.nonblocking;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
}