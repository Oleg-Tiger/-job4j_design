package ru.job4j.collection.set;

import ru.job4j.collection.SimpleArray;
import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    private SimpleArray<T> set = new SimpleArray<>();

    @Override
    public boolean add(T value) {
        if (!contains(value)) {
            set.add(value);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T value) {
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            T next = it.next();
            try {
                if (next.equals(value)) {
                    return true;
                }
            } catch (NullPointerException e) {
                if (next == value) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}