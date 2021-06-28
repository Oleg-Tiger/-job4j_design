package ru.job4j.search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NameArgs {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        Arrays.stream(args)
                .map(x -> x.split("="))
                .forEach(x -> {
                    if (x.length != 2) {
                        throw new IllegalArgumentException("The parameters must match the template : \"-key=value\"");
                    }
                    values.put(x[0].substring(1), x[1]);
                });
    }

    public static NameArgs of(String[] args) {
        NameArgs names = new NameArgs();
        names.parse(args);
        return names;
    }

}
