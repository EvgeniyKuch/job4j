package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] array;
    private int position = 0;

    public EvenNumbersIterator(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return nextIndex() != -1;
    }

    @Override
    public Integer next() {
        int next = this.nextIndex();
        if (next == -1) {
            throw new NoSuchElementException();
        }
        position = next + 1;
        return this.array[next];
    }

    private int nextIndex() {
        int index = -1;
        for (int i = position; i != this.array.length && index == -1; i++) {
            index = this.array[i] % 2 == 0 ? i : -1;
        }
        return index;
    }
}
