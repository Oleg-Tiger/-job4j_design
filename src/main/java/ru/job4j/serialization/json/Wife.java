package ru.job4j.serialization.json;

import org.json.JSONObject;
import org.json.JSONPropertyIgnore;

public class Wife {
    private Husband husband;

    @JSONPropertyIgnore
    public Husband getHusband() {
        return husband;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }

    public static void main(String[] args) {
        Husband husband = new Husband();
        Wife wife = new Wife();
        husband.setWife(wife);
        wife.setHusband(husband);

        System.out.println(new JSONObject(husband));
    }
}
