package ru.job4j.collection.mail;

import java.util.Objects;
import java.util.Set;

public class User {
    private String name;
    private Set<Email> mailSet;

    public User(String name, Set<Email> mailSet) {
        this.name = name;
        this.mailSet = mailSet;
    }

    public String getName() {
        return name;
    }

    public Set<Email> getMailSet() {
        return mailSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name)
                && Objects.equals(mailSet, user.mailSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, mailSet);
    }
}
