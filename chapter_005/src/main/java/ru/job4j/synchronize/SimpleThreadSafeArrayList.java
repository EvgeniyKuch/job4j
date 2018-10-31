package ru.job4j.synchronize;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.SimpleArrayListDynamics;

import java.util.Iterator;

@ThreadSafe
public class SimpleThreadSafeArrayList<E> implements Iterable<E> {

    @GuardedBy("this")
    final SimpleArrayListDynamics<E> array;

    public SimpleThreadSafeArrayList() {
        this.array = new SimpleArrayListDynamics<>();
    }

    public synchronized void add(E value) {
        this.array.add(value);
    }

    public synchronized E get(int index) {
        return this.array.get(index);
    }

    private synchronized SimpleArrayListDynamics<E> copy() {
        SimpleArrayListDynamics<E> copy = new SimpleArrayListDynamics<>();
        for (E element : this.array) {
            copy.add(element);
        }
        return copy;
    }

    @Override
    public Iterator<E> iterator() {
        return this.copy().iterator();
    }
}
