package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> {

    private Pair<K, V>[] map;
    private int size;
    private int modCount;
    private final double loadFactor = 0.5;

    public SimpleHashMap() {
        this.map = new Pair[16];
        this.size = 0;
        this.modCount = 0;
    }

    private int hash(Object key) {
        int h = key == null ? 0 : key.hashCode();
        return h ^ (h >>> 16);
    }

    /**
     * Вставляет пару в хеш-таблицу.
     * Пару с тем же ключом перезаписывает.
     * В случае коллизии (хеш один, а ключ разный)
     * запись не производит и возвращает false.
     * @param key - ключ.
     * @param value - значение.
     * @return true - запись произведена, false -
     * запись не произведена.
     */
    public boolean insert(K key, V value) {
        if ((double) size / map.length > loadFactor) {
            resize();
        }
        return insertPair(key, value, map);
    }

    private boolean insertPair(K key, V value, Pair[] table) {
        boolean result = false;
        int hash = hash(key);
        int index = hash % table.length;
        if (table[index] == null || table[index].hash == hash && table[index].key.equals(key)) {
            table[index] = new Pair(hash, key, value);
            // если расширяем и перехешируем таблицу,
            // то не меняем size и modCount
            if (table == map) {
                size++;
                modCount++;
            }
            result = true;
        }
        return result;
    }

    private void resize() {
        Pair<K, V>[] newMap = new Pair[map.length * 2];
        for (Pair<K, V> pair : map) {
            if (pair != null) {
                insertPair(pair.key, pair.value, newMap);
            }
        }
        map = newMap;
    }

    public V get(K key) {
        int index = hash(key) % map.length;
        return map[index] != null ? map[index].value : null;
    }

    public boolean delete(K key) {
        int index = hash(key) % map.length;
        boolean result = map[index] != null;
        map[index] = null;
        size--;
        modCount++;
        return result;
    }

    private class Pair<K, V> {
        final int hash;
        final K key;
        V value;

        public Pair(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }

    public Iterator<K> iteratorKeys() {
        return new ItrKeys();
    }

    public Iterator<V> iteratorValues() {
        return new ItrValues();
    }

    private class ItrPair implements Iterator<Pair<K, V>> {

        private int cursor = 0;
        private int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            while (cursor < map.length && map[cursor] == null) {
                cursor++;
            }
            return cursor < map.length;
        }

        @Override
        public Pair<K, V> next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return map[cursor++];
        }
    }

    private class ItrKeys implements Iterator<K> {

        private Iterator<Pair<K, V>> itr = new ItrPair();

        @Override
        public boolean hasNext() {
            return itr.hasNext();
        }

        @Override
        public K next() {
            return itr.next().key;
        }
    }

    private class ItrValues implements Iterator<V> {

        private Iterator<Pair<K, V>> itr = new ItrPair();

        @Override
        public boolean hasNext() {
            return itr.hasNext();
        }

        @Override
        public V next() {
            return itr.next().value;
        }
    }
}
