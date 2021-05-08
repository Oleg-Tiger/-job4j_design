package ru.job4j.collection.map;

import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleMapTest {

    @Test
    public void whenInsertAndGet() {
        SimpleMap<String, String> sm = new SimpleMap<>();
        assertTrue(sm.insert("Один", "One"));
        assertThat(sm.get("Один"), is("One"));
        assertNull(sm.get("Два"));
    }

    @Test
    public void whenTheArrayExpands() {
        SimpleMap<Integer, String> sm = new SimpleMap<>(2);
        assertTrue(sm.insert(1, "One"));
        assertTrue(sm.insert(2, "Two"));
        assertTrue(sm.insert(3, "Three"));
        assertThat(sm.get(2), is("Two"));
        assertThat(sm.get(1), is("One"));
        assertThat(sm.get(3), is("Three"));
    }

    @Test
    public void whenKeyIsNull() {
        SimpleMap<String, String> sm = new SimpleMap<>();
        assertTrue(sm.insert(null, "Один"));
        assertTrue(sm.insert(null, "Два"));
        assertThat(sm.get(null), is("Два"));
    }

    @Test
    public void whenDelete() {
        SimpleMap<String, String> sm = new SimpleMap<>();
        assertTrue(sm.insert("Один", "One"));
        assertTrue(sm.delete("Один"));
        assertFalse(sm.delete("Один"));
    }

    @Test
    public void testIterator() {
        SimpleMap<String, String> sm = new SimpleMap<>();
        sm.insert("Один", "One");
        sm.insert("Два", "Two");
        Iterator<SimpleMap.Node<String, String>> it = sm.iterator();
        assertThat(it.next().getKey(), is("Один"));
        assertTrue(it.hasNext());
        assertThat(it.next().getValue(), is("Two"));
        assertFalse(it.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
        public void whenInsert() {
        SimpleMap<String, String> sm = new SimpleMap<>();
        sm.insert("Один", "One");
        Iterator<SimpleMap.Node<String, String>> it = sm.iterator();
        sm.insert("Два", "Two");
        it.hasNext();
        }

    @Test(expected = ConcurrentModificationException.class)
    public void whenRemove() {
        SimpleMap<String, String> sm = new SimpleMap<>();
        sm.insert("Один", "One");
        Iterator<SimpleMap.Node<String, String>> it = sm.iterator();
        sm.delete("Один");
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenHasNotNext() {
        SimpleMap<String, String> sm = new SimpleMap<>();
        Iterator<SimpleMap.Node<String, String>> it = sm.iterator();
        it.next();
    }
}