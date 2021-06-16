package ru.job4j.serialization.json;

public class Husband {
    private Wife wife;

    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }

    public String getLoveWife() {
        return "I love my Wife!!!";
    }
}
