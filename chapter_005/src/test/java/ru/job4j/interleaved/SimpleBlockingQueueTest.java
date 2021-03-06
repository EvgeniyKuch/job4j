package ru.job4j.interleaved;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    class Producer implements Runnable {

        private final SimpleBlockingQueue<Integer> queue;

        public Producer(SimpleBlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    queue.offer(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumer implements Runnable {

        private final SimpleBlockingQueue<Integer> queue;

        public Consumer(SimpleBlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                int result = 0;
                try {
                    result = queue.poll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //такой тест проходит
                assertThat(result, is(i));
                //а такой тест падает и виснет
                //assertThat(result, is(i + 1));
            }
        }
    }

    @Test
    public void whenThen() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
        Thread first = new Thread(new Producer(queue));
        Thread second = new Thread(new Consumer(queue));
        first.start();
        second.start();
        first.join();
        second.join();
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        Thread producer = new Thread(() ->
                IntStream.range(0, 5).forEach((i) -> {
                            try {
                                queue.offer(i);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                ));
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        producer.start();
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }
}