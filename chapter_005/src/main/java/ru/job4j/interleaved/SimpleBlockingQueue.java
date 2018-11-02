package ru.job4j.interleaved;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.SimpleQueue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final SimpleQueue<T> queue = new SimpleQueue<>();
    @GuardedBy("this")
    private int size;
    private final int maxSize;

    public SimpleBlockingQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public synchronized void offer(T value) {
        while (!Thread.currentThread().isInterrupted() && size >= maxSize) {
            try {
                System.out.println("Queue is full");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Put: " + value);
        queue.push(value);
        size++;
        notifyAll();
    }

    public synchronized T poll() {
        while (!Thread.currentThread().isInterrupted() && size == 0) {
            try {
                System.out.println("Queue is empty");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        T result = queue.poll();
        System.out.println("Get: " + result);
        size--;
        notifyAll();
        return result;
    }
}
