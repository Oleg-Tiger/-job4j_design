package ru.job4j.collection.map;

import java.util.*;

public class SimpleMap<K, V> implements Iterable<SimpleMap.Node<K, V>> {
    private Node<K, V>[] array;
    private int size = 0;
    private int modCount = 0;
    private static final float LOAD_FACTOR = 0.75F;
    private static final short DEFAULT_SIZE = 16;
    private static final short BIT_SHIFT = 16;
    private static final short EXPANSION_COEFFICIENT = 2;

    public SimpleMap() {
        this.array = new Node[DEFAULT_SIZE];
    }

    public SimpleMap(int num) {
        this.array = new Node[num];
    }

    public boolean insert(K key, V value) {
        if ((float) size / array.length >= LOAD_FACTOR) {
            expansion();
        }
        int hash = hash(key);
        Node<K, V> node = new Node<>(key, value, hash);
        int index = findIndex(hash);
        if (!cellIsEmpty(index) && keysAreDiff(hash, index, key)) {
            return false;
        }
        if (cellIsEmpty(index)) {
            size++;
        }
        modCount++;
        array[index] = node;
        return true;
    }

    public V get(K key) {
        int hash = hash(key);
        int index = findIndex(hash);
       if (cellIsEmpty(index) || keysAreDiff(hash, index, key)) {
            return null;
        }
        return array[index].value;
    }

    public boolean delete(K key) {
        int hash = hash(key);
        int index = findIndex(hash);
        if (cellIsEmpty(index) || keysAreDiff(hash, index, key)) {
            return false;
        }
        modCount++;
        size--;
        array[index] = null;
        return true;
    }

    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        return key.hashCode() ^ (key.hashCode() >>> BIT_SHIFT);
    }

    private int findIndex(int hash) {
       return hash & (array.length - 1);
    }

    private boolean cellIsEmpty(int index) {
        return array[index] == null;
    }

    private boolean keysAreDiff(int hash, int index, K key) {
        return (array[index].hash != hash
                || (array[index].key != key && !array[index].key.equals(key)));
    }

    private void expansion() {
        SimpleMap<K, V> map = new SimpleMap<>(array.length * EXPANSION_COEFFICIENT);
        for (Node<K, V> temp : this) {
            map.insert(temp.key, temp.value);
        }
        array = map.array;
    }

    @Override
    public Iterator<SimpleMap.Node<K, V>> iterator() {
       return new Iterator<>() {
            private int cursor = 0;
            final int expectedModCount = modCount;

           @Override
           public boolean hasNext() {
               if (expectedModCount != modCount) {
                   throw new ConcurrentModificationException();
               }
               for (int i = cursor; i < array.length; i++) {
                   if (array[i] != null) {
                       cursor = i;
                       return true;
                   }
               }
               return false;
           }

            @Override
            public Node<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[cursor++];
            }
        };
    }

    static class Node<K, V>  {
        private final int hash;
        private final K key;
        private final V value;

        private Node(K key, V value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "{"
                    + "key="
                    + key
                    + ", value="
                    + value
                    + '}';
         }
    }
}

