package ru.job4j.collection;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import ru.job4j.collection.Analize.User;
import ru.job4j.collection.Analize.Info;

public class AnalizeTest {

    @Test
    public void whenAdded1() {
        List<User> previous = Arrays.asList(new User(123, "Олег"));
        List<User> current = new ArrayList<>(previous);
        current.add(new User(12, "Вася"));
        Info rsl = new Analize().diff(previous, current);
        assertThat(rsl.added, is(1));
        assertThat(rsl.deleted, is(0));
        assertThat(rsl.changed, is(0));
    }

    @Test
    public void when2Removed() {
        List<User> previous = Arrays.asList(
                new User(123, "Олег"),
                new User(12, "Вася"),
                new User(1, "Иван")
        );
        List<User> current = new ArrayList<>(previous);
        current.remove(0);
        current.remove(0);
        Info rsl = new Analize().diff(previous, current);
        assertThat(rsl.added, is(0));
        assertThat(rsl.deleted, is(2));
        assertThat(rsl.changed, is(0));
    }

    @Test
    public void when1Changed() {
        List<User> previous = Arrays.asList(
                new User(123, "Олег"),
                new User(12, "Вася")
        );
        List<User> current = new ArrayList<>(previous);
        current.set(1, new User(current.get(1).id, "Иван"));
        Info rsl = new Analize().diff(previous, current);
        assertThat(rsl.added, is(0));
        assertThat(rsl.deleted, is(0));
        assertThat(rsl.changed, is(1));
    }

    @Test
    public void when2Changed2Added1Removed() {
        List<User> previous = Arrays.asList(
                new User(123, "Олег"),
                new User(12, "Вася"),
                new User(1, "Иван")
        );
        List<User> current = new ArrayList<>(previous);
        current.set(0, new User(1234, "Олег"));
        current.add(new User(12345, "Андрей"));
        current.set(1, new User(current.get(1).id, "Иван"));
        current.set(2, new User(current.get(2).id, "Вася"));
        Info rsl = new Analize().diff(previous, current);
        assertThat(rsl.added, is(2));
        assertThat(rsl.deleted, is(1));
        assertThat(rsl.changed, is(2));
    }
}