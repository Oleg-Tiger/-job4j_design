package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {

    public static List<String> filter(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines()
                    .filter(x -> x.contains(" 404 "))
                    .filter(x -> {
                       List<String> list = Arrays.stream(x.split(" "))
                                .filter(y -> isNumber(y))
                                .collect(Collectors.toList());
                       return list.size() > 1 && list.get(list.size() - 2).equals("404");
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean isNumber(String str) {
     try {
        Integer.parseInt(str);
    } catch (NumberFormatException e) {
        return false;
    }
    return true;
}

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}