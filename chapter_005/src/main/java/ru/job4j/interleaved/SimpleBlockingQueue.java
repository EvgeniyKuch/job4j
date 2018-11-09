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

    public synchronized void offer(T value) throws InterruptedException {
        while (size >= maxSize) {
            wait();
        }
        queue.push(value);
        size++;
        notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
        while (size == 0) {
            wait();
        }
        T result = queue.poll();
        size--;
        notifyAll();
        return result;
    }
}
