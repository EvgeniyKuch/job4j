package ru.job4j.list;

public class SimpleQueue<T> {

    private SimpleLinkedListDynamics<T> queue = new SimpleLinkedListDynamics<>();

    public T poll() {
        return this.queue.deleteFirst();
    }

    public void push(T value) {
        this.queue.add(value);
    }
}
