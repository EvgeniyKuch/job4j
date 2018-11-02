package ru.job4j.interleaved;

import org.junit.Test;

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
                queue.offer(i);
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
                int result = queue.poll();
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
}