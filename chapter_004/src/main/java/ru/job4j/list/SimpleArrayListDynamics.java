package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayListDynamics<E> implements Iterable<E> {

    private Object[] container;
    private int position = 0;
    private int modCount = 0;

    public SimpleArrayListDynamics() {
        this.container = new Object[10];
    }

    public void add(E value) {
        modCount++;
        if (position == container.length) {
            grow();
        }
        this.container[position++] = value;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= position) {
            throw new IndexOutOfBoundsException();
        }
        return (E) this.container[index];
    }

    private void grow() {
        container = Arrays.copyOf(container,
                container.length + container.length / 2);
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {

        private int cursor = 0;
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor != SimpleArrayListDynamics.this.position;
        }

        @Override
        public E next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return SimpleArrayListDynamics.this.get(cursor++);
        }
    }
}
