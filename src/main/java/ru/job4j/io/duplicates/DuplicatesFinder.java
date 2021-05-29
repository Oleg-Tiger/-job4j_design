package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Map<FileProperty, Set<Path>> rsl = findDuplicates(args[0]);
        for (Map.Entry<FileProperty, Set<Path>> pair : rsl.entrySet()) {
            System.out.printf("Файл c именем %s размером %s байт содержится в несколких директориях: ",
                    pair.getKey().getName(), pair.getKey().getSize());
            for (Path paths : pair.getValue()) {
                System.out.printf("%s; ", paths);
            }
            System.out.println();
        }
    }

    public static Map<FileProperty, Set<Path>> findDuplicates(String start) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of(start), visitor);
        return visitor.getDuplicates();
    }
}