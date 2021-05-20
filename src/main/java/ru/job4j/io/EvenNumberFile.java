package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        try (FileInputStream fis = new FileInputStream("even.txt")) {
            int read;
            while ((read = fis.read()) != -1) {
                sb.append((char) read);
            }
            String[] rsl = sb.toString().split(System.lineSeparator());
            for (String str : rsl) {
                System.out.println("Является ли число " + str + " чётным?");
                System.out.println(Integer.parseInt(str) % 2 == 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}