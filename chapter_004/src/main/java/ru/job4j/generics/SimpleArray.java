package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] array;
    private int position = 0;

    public SimpleArray(int quantity) {
        this.array = new Object[quantity];
    }

    public void add(T model) {
        if (this.position == this.array.length) {
            throw new SimpleArrayOverflowException();
        }
        this.array[position++] = model;
    }

    public void set(int index, T model) {
        if (index < 0 || index >= position) {
            throw new IllegalIndexException();
        }
        this.array[index] = model;
    }

    public void delete(int index) {
        if (index < 0 || index >= position) {
            throw new IllegalIndexException();
        }
        System.arraycopy(this.array, index + 1, this.array, index, position - index - 1);
        this.position--;
    }

    public T get(int index) {
        if (index < 0 || index >= position) {
            throw new IllegalIndexException();
        }
        return (T) this.array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {

        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor != SimpleArray.this.position;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return SimpleArray.this.get(cursor++);
        }
    }
}
