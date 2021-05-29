package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, Set<Path>> duplicates = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fp = new FileProperty(Files.size(file), file.getFileName().toString());
        if (!duplicates.containsKey(fp)) {
            Set<Path> set = new HashSet<>(Arrays.asList(file.getParent()));
            duplicates.put(fp, set);
        } else {
            duplicates.get(fp).add(file.getParent());
        }
        return super.visitFile(file, attrs);
    }

    public Map<FileProperty, Set<Path>> getDuplicates() {
        Map<FileProperty, Set<Path>> rsl = new HashMap<>();
        for (Map.Entry<FileProperty, Set<Path>> pair : duplicates.entrySet()) {
            if (pair.getValue().size() > 1) {
                rsl.put(pair.getKey(), pair.getValue());
            }
        }
        return rsl;
    }
}