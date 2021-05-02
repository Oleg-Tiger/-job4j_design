package ru.job4j.collection;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.hash;

public class MapTest {
    public static void main(String[] args) {
        User user = new User("Вася", 3, new GregorianCalendar(1990, 0, 21));
        User user2 = new User("Вася", 3, new GregorianCalendar(1990, 0, 21));
        Map<User, Object> map = new HashMap<>();
        map.put(user, new Object());
        map.put(user2, new Object());
        System.out.println(map);
    }
}
