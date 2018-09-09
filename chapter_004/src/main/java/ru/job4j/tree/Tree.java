package ru.job4j.tree;

import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    private final Node<E> root;
    private int modCount = 0;

    public Tree(E value) {
        this.root = new Node<>(value);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> parentNode = this.findBy(parent);
        boolean result =
                !this.findBy(child).isPresent() && parentNode.isPresent();
        if (result) {
            parentNode.get().add(new Node<>(child));
            modCount++;
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    public boolean isBinary() {
        return new Itr().bynar();
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {

        private Queue<Node<E>> queue;
        private int expectedModCount;
        private boolean isBinary = true;

        public Itr() {
            this.queue = new LinkedList<>();
            this.queue.offer(root);
            this.expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public E next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<E> result = queue.poll();
            if (!result.leaves().isEmpty()) {
                this.isBinary = result.leaves().size() <= 2;
                for (Node<E> child : result.leaves()) {
                    queue.offer(child);
                }
            }
            return result.getValue();
        }

        public boolean bynar() {
            while (hasNext() && isBinary) { // пока есть элементы и детей меньше 3, тем самым избавляемся от полного перебора дерева
                next();
            }
            return isBinary;
        }
    }
}
