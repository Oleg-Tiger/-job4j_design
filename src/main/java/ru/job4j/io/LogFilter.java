package ru.job4j.io;

import java.io.*;
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
                                .filter(LogFilter::isNumber)
                                .collect(Collectors.toList());
                       return list.size() > 1 && list.get(list.size() - 2).equals("404");
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void save(List<String> log, String file) {
        try (
                PrintWriter out = new PrintWriter(
                        new BufferedOutputStream(
                                new FileOutputStream(file)
                        ))) {
            log.forEach(x -> out.write((x + System.lineSeparator())));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        save(log, "404.txt");
    }
}