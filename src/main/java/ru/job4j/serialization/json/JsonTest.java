package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

public class JsonTest {
    public static void main(String[] args) {
        final Person owner = new Person("Oleg", 29, "Voskresensk");
        final Dog zlata = new Dog(false, 1, "Zlata", owner, "Лежать", "Сидеть", "Фас");

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(zlata));

        /* Модифицируем json-строку. */
        final String jackJson = "{"
                + "\"sex\":true,"
                + "\"age\":2,"
                + "\"name\":\"Jack\","
                + "\"owner\":"
                     + "{"
                        + "\"name\":\"Artem\","
                        + "\"age\":31,"
                        + "\"city\":\"Voskresensk\""
                     + "},"
                + "\"commands\":[\"Лежать\",\"Дай лапу\"]"
                + "}";

        /* Создаём объект Dog из json-строки. */
        final Dog jack = gson.fromJson(jackJson, Dog.class);
        System.out.println(jack);

        /* Создаём JSONObject напрямую методом put */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", zlata.isSex());
        jsonObject.put("age", zlata.getAge());
        jsonObject.put("name", zlata.getName());
        jsonObject.put("owner", zlata.getOwner());
        jsonObject.put("commands", zlata.getCommands());

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект zlata в json-строку */
        System.out.println(new JSONObject(zlata).toString());
    }
}
