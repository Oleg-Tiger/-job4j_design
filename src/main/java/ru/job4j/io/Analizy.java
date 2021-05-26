package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
                PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            boolean lastStatus400Or500 = false;
            while (read.ready()) {
                String[] str = read.readLine().split(" ");
                if (str.length != 2) {
                    throw new IllegalArgumentException();
                } else if (str[0].equals("400") || str[0].equals("500")) {
                    if (!lastStatus400Or500) {
                        out.printf("%s%s", str[1], ";");
                        lastStatus400Or500 = true;
                    }
                } else {
                    if (lastStatus400Or500) {
                        out.printf("%s%s%n", str[1], ";");
                        lastStatus400Or500 = false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Analizy().unavailable("./data/server.log", "./data/unavailable.csv");
    }
}