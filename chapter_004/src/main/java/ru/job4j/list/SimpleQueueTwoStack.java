package ru.job4j.list;

public class SimpleQueueTwoStack<T> {

    private final SimpleStack<T> original = new SimpleStack<>();
    private final SimpleStack<T> buffer = new SimpleStack<>();

    public void push(T value) {
        transfuse(original, buffer);
        original.push(value);
        transfuse(buffer, original);
    }

    public T poll() {
        return original.poll();
    }

    private void transfuse(SimpleStack<T> full, SimpleStack<T> empty) {
        for (T i = full.poll(); i != null; i = full.poll()) {
            empty.push(i);
        }
    }
}
