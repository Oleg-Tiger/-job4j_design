package ru.job4j.io;

import java.io.FileOutputStream;

public class Matrix {
    public void multiple(int size) {
        try (FileOutputStream fos = new FileOutputStream("matrix.txt")) {
            for (int i = 1; i <= size; i++) {
                for (int j = 1; j <= size; j++) {
                    String str = i * j + "\t";
                    fos.write(str.getBytes());
                }
                fos.write("\n".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Matrix().multiple(10);
    }
}