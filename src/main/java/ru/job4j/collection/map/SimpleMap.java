package ru.job4j.collection.map;

import java.util.*;

public class SimpleMap<K, V> implements Iterable<SimpleMap.Node> {
    private Node<K, V>[] array;
    private int size = 0;
    private int modCount = 0;

    public SimpleMap() {
        this.array = new Node[16];
    }

    public SimpleMap(int num) {
        this.array = new Node[num];
    }

    public boolean insert(K key, V value) {
        if ((double) size / array.length >= 0.75) {
            expansion();
        }
        int hash = hash(key);
        Node<K, V> node = new Node<>(key, value, hash);
        int index = findIndex(hash);
        if (!cellIsEmpty(index) && !keysAreSame(hash, index, key)) {
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
       if (cellIsEmpty(index) || !keysAreSame(hash, index, key)) {
            return null;
        }
        return array[index].value;
    }

    public boolean delete(K key) {
        int hash = hash(key);
        int index = findIndex(hash);
        if (cellIsEmpty(index) || !keysAreSame(hash, index, key)) {
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
        return key.hashCode() ^ (key.hashCode() >>> 16);
    }

    private int findIndex(int hash) {
       return hash & (array.length - 1);
    }

    private boolean cellIsEmpty(int index) {
        return array[index] == null;
    }

    private boolean keysAreSame(int hash, int index, K key) {
        return (array[index].hash == hash
                && (array[index].key == key || array[index].key.equals(key)));
    }

    private void expansion() {
        SimpleMap<K, V> map = new SimpleMap<>(array.length * 2);
        Iterator<SimpleMap.Node> it = iterator();
        while (it.hasNext()) {
            Node<K, V> temp = it.next();
            map.insert(temp.key, temp.value);
        }
        array = map.array;
    }

    @Override
    public Iterator<SimpleMap.Node> iterator() {
       return new Iterator<>() {
            private int cursor = 0;
            final int expectedModCount = modCount;
            private Object[] temp = Arrays.stream(array)
            .filter(Objects::nonNull)
            .toArray();

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }

            @Override
            public Node<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
             return (Node<K, V>) temp[cursor++];
            }
        };
    }

    public class Node<K, V>  {
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

