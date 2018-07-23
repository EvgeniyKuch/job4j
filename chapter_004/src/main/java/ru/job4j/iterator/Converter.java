package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {

            private Iterator<Integer> nextIterator;
            private Integer nextInteger;

            @Override
            public boolean hasNext() {
                while (it.hasNext() && nextInteger == null) {
                    nextIterator = it.next();
                    nextInteger = nextIterator.hasNext()
                            ? nextIterator.next() : null;
                }
                return nextInteger != null;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int result = nextInteger;
                nextInteger = nextIterator.hasNext()
                        ? nextIterator.next() : null;
                hasNext();
                return result;
            }
        };
    }
}
