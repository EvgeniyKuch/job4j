package ru.job4j.trie;

import java.util.HashMap;
import java.util.Map;

public class Trie<V> {

    private final Node<V> root;
    private int size = 0;

    public Trie() {
        this.root = new Node<>(null);
    }

    public void put(String key, V value) {
        Node<V> current = root;
        for (int i = 0; i != key.length(); i++) {
            char code = key.charAt(i);
            current.map.putIfAbsent(code, new Node<>(code));
            current = current.map.get(code);
        }
        size = current.date == null ? size + 1 : size;
        current.date = value;
    }

    public V get(String key) {
        Node<V> result = search(key);
        return result == null ? null : result.date;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public V delete(String key) {
        Node<V> deleted = search(key);
        V result = null;
        if (deleted != null) {
            result = deleted.date;
            size = result != null ? size - 1 : size;
            deleted.date = null;
        }
        return result;
    }

    private Node<V> search(String key) {
        Node<V> current = root;
        for (int i = 0; i != key.length() && current != null; i++) {
            current = current.map.get(key.charAt(i));
        }
        return current;
    }

    private class Node<E> {

        final Character character;
        E date;
        Map<Character, Node<E>> map = new HashMap<>();

        public Node(Character character) {
            this.character = character;
        }
    }
}
