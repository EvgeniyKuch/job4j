package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedListDynamics<E> implements Iterable<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;
    private int modCount;

    /**
     * Метод вставляет данные.
     */
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        if (first == null) {
            first = newLink;
            last = first;
        } else if (first == last) {
            last = newLink;
            last.prev = first;
            first.next = last;
        } else {
            last.next = newLink;
            newLink.prev = last;
            last = newLink;
        }
        size++;
        modCount++;
    }

    /**
     * Возвращает данные по индексу.
     * @param index индекс.
     * @return возвращаемое значение.
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E result;
        if (index < size / 2) {
            result = getWithBegin(index);
        } else {
            result = getWithEnd(index);
        }
        return result;
    }

    /**
     * Удаляет первый элемент и возвращает его.
     * @return удалаяемый элемент.
     */
    public E deleteFirst() {
        E result = null;
        if (first != null) {
            result = first.date;
            first = first.next;
            if (first == null) {
                last = null;
            } else {
                first.prev = null;
            }
            size--;
            modCount++;
        }
        return result;
    }

    /**
     * Удааляет последний элемент и возвращает его.
      * @return удаляемый элемент.
     */
    public E deleteLast() {
        E result = null;
        if (last != null) {
            result = last.date;
            last = last.prev;
            if (last == null) {
                first = null;
            } else {
                last.next = null;
            }
            size--;
            modCount++;
        }
        return result;
    }

    /**
     * Обход с начала списка.
     */
    private E getWithBegin(int index) {
        Node<E> result = this.first;
        for (int i = 0; i != index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Обход с конца списка.
     */
    private E getWithEnd(int index) {
        Node<E> result = this.last;
        for (int i = size - 1; i != index; i--) {
            result = result.prev;
        }
        return result.date;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {

        E date;
        Node<E> next;
        Node<E> prev;

        Node(E date) {
            this.date = date;
        }
    }

    private class Itr implements Iterator<E> {

        private Node<E> cursor = SimpleLinkedListDynamics.this.first;
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public E next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E result = cursor.date;
            cursor = cursor.next;
            return result;
        }
    }
}
