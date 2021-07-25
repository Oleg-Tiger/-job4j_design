package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CSVReader {

    public static void main(String[] args) throws FileNotFoundException {
        ArgsName argsName = ArgsName.of(args);
        readFile(argsName);
    }

    private static void readFile(ArgsName argsName) throws FileNotFoundException {
        String pathCSV = argsName.get("path");
        if (!pathCSV.endsWith(".csv")) {
            throw new IllegalArgumentException("the path parameter must have the extension .csv");
        }
        String path = pathCSV.substring(0, pathCSV.length() - 3).concat("txt");
        String delimiter = argsName.get("delimiter");
        if (!delimiter.equals(";") && !delimiter.equals(",")) {
            throw new IllegalArgumentException("as a separator use \";\" or \",\"");
        }
        Set<String> filters = Arrays.stream(argsName.get("filter").split(","))
                .collect(Collectors.toSet());
        String out = argsName.get("out");
        if (out.equals("stdout")) {
            write(System.out, filters, path, delimiter);
        } else {
            try (PrintStream ps = new PrintStream(new File(out))) {
                write(ps, filters, path, delimiter);
            }
        }
    }

    private static void write(PrintStream ps, Set<String> filters, String path, String delimiter)
            throws FileNotFoundException {
        boolean firstLine = true;
        Set<Integer> indexes = new HashSet<>();
        try (Scanner scanner = new Scanner(new File(path));
             PrintStream printStream = new PrintStream(ps)) {
            while (scanner.hasNextLine()) {
                String[] columns = scanner.nextLine().split(delimiter);
                for (int i = 0; i < columns.length; i++) {
                    String columnWithoutSpace = columns[i].trim();
                    if (firstLine) {
                        if (filters.contains(columnWithoutSpace)) {
                            printStream.printf("\t%s\t ", columnWithoutSpace);
                            indexes.add(i);
                        }
                    } else {
                        if (indexes.contains(i)) {
                            printStream.printf("\t%s\t ", columnWithoutSpace);
                        }
                    }
                }
                printStream.println();
                firstLine = false;
            }
        }
    }
}
