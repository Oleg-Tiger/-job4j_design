package ru.job4j.collection;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;


public class MapTest {
    public static void main(String[] args) {
        Calendar date = new GregorianCalendar(1990, 11, 12);
        User user = new User("Вася", 3, date);
        User user2 = new User("Вася", 3, date);
        Map<User, Object> map = new HashMap<>();
        map.put(user, new Object());
        map.put(user2, new Object());
        System.out.println(map);
    }
}
