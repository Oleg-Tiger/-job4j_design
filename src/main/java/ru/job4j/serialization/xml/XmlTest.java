package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import ru.job4j.serialization.json.Dog;
import ru.job4j.serialization.json.Person;

public class XmlTest {
    public static void main(String[] args) throws Exception {
        final Person owner = new Person("Oleg", 29, "Voskresensk");
        final Dog zlata = new Dog(false, 1, "Zlata", owner, "Лежать", "Сидеть", "Фас");
        // Получаем контекст для доступа к АПИ
        JAXBContext context = JAXBContext.newInstance(Dog.class);
        // Создаем сериализатор
        Marshaller marshaller = context.createMarshaller();
        // Указываем, что нам нужно форматирование
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            // Сериализуем
            marshaller.marshal(zlata, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        // Для десериализации нам нужно создать десериализатор
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            // десериализуем
            Dog result = (Dog) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
