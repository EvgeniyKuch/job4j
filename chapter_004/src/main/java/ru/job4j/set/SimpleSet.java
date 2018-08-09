package ru.job4j.set;

import ru.job4j.list.SimpleArrayListDynamics;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {

    private SimpleArrayListDynamics<E> set = new SimpleArrayListDynamics<>();

    public boolean add(E e) {
        boolean result = !this.contains(e);
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
            }
        }
        return result;
    }


    @Override
    public Iterator<E> iterator() {
        return this.set.iterator();
    }
}
