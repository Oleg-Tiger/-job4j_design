package ru.job4j.generics;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] array;
    private int size = 0;

    public SimpleArray(int size) {
         this.array = new Object[size];
    }

    public boolean add(T model) {
      if (size < array.length) {
          array[size++] = model;
          return true;
       }
       return false;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, size);
        array[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        System.arraycopy(array, index + 1, array, index, array.length - index - 1);
        array[size - 1] = null;
        size--;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int cursor = 0;

            @Override
            public boolean hasNext() {
            return cursor < size;
            }

            @Override
            public T next() {
                return (T) array[cursor++];
            }
        };
    }
}
