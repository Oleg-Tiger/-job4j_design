package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "zlata")
@XmlAccessorType(XmlAccessType.FIELD)
public class Dog {
    @XmlAttribute
    private boolean sex;

    @XmlAttribute
    private int age;

    @XmlAttribute
    private String name;
    private Person owner;

    @XmlElementWrapper(name = "commands")
    @XmlElement(name = "command")
    private String[] commands;

    public Dog() {
    }

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
