package ru.job4j.serialization.json;

import java.util.Arrays;

public class Dog {
    private final boolean sex;
    private final int age;
    private final String name;
    private final Person owner;
    private final String[] commands;

    public Dog(boolean sex, int age, String name, Person owner, String... commands) {
        this.sex = sex;
        this.age = age;
        this.name = name;
        this.owner = owner;
        this.commands = commands;
    }

    @Override
    public String toString() {
        return "Dog{"
                + "sex=" + sex
                + ", age=" + age
                + ", name='" + name + '\''
                + ", owner=" + owner
                + ", commands=" + Arrays.toString(commands)
                + '}';
    }
}
