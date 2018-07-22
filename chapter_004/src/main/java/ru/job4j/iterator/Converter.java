package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {

            private Iterator<Integer> currentIterator;
            private Integer currentInteger;
            private boolean initial = false;

            @Override
            public boolean hasNext() {
                initial();
                return currentInteger != null;
            }

            @Override
            public Integer next() {
                initial();
                if (currentInteger == null) {
                    throw new NoSuchElementException();
                }
                int result = currentInteger;
                if (currentIterator.hasNext()) {
                    currentInteger = currentIterator.next();
                } else {
                    currentInteger = null;
                    searchNext();
                }
                return result;
            }

            private void searchNext() {
                while (it.hasNext()) {
                    currentIterator = it.next();
                    if (currentIterator.hasNext()) {
                        currentInteger = currentIterator.next();
                        break;
                    }
                }
            }

            private void initial() {
                if (!initial) {
                    searchNext();
                    initial = true;
                }
            }
        };
    }
}
