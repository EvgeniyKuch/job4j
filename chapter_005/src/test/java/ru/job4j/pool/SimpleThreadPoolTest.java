package ru.job4j.pool;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleThreadPoolTest {

    @Test
    public void when10000ThreadsIncrementCountThenCountIs10000() {
        SimpleThreadPool pool = new SimpleThreadPool();
        AtomicInteger count = new AtomicInteger(0);
        for (int i = 0; i != 10000; i++) {
            pool.work(count::incrementAndGet);
        }
        pool.shutdown();
        pool.awaitTermination();
        assertThat(count.get(), is(10000));
    }
}