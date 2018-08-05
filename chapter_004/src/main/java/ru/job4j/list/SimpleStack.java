package ru.job4j.list;

public class SimpleStack<T> {

    private SimpleLinkedListDynamics<T> stack = new SimpleLinkedListDynamics<>();

    public T poll() {
        return this.stack.deleteLast();
    }

    public void push(T value) {
        this.stack.add(value);
    }
}
