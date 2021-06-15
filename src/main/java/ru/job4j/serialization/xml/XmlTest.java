package ru.job4j.serialization.xml;

import ru.job4j.serialization.json.Dog;
import ru.job4j.serialization.json.Person;

public class XmlTest {
    final Person owner = new Person("Oleg", 29, "Voskresensk");
    final Dog zlata = new Dog(false, 1, "Zlata", owner, "Лежать", "Сидеть", "Фас");
}
