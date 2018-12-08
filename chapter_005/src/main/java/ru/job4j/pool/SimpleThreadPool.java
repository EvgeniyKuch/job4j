package ru.job4j.pool;

import ru.job4j.interleaved.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class SimpleThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public SimpleThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i != size; i++) {
            Thread thread = new Thread(
                    () -> {
                        try {
                            while (!tasks.isEmpty()
                                    || !Thread.currentThread().isInterrupted()) {
                                tasks.poll().run();
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
            );
            this.threads.add(thread);
            thread.start();
        }
    }

    public void work(Runnable job) {
        try {
            tasks.offer(job);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }

    public void awaitTermination() {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
