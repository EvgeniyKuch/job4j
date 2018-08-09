package ru.job4j.set;

import ru.job4j.list.SimpleLinkedListDynamics;

import java.util.Iterator;

public class SimpleLinkedSet<E> implements Iterable<E> {

    private SimpleLinkedListDynamics<E> set = new SimpleLinkedListDynamics<>();

    public boolean add(E e) {
        boolean result = !contains(e);
        if (result) {
            this.set.add(e);
        }
        return result;
    }

    private boolean contains(E e) {
        boolean result = false;
        for (E each : set) {
            if (each.equals(e)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return this.set.iterator();
    }
}
