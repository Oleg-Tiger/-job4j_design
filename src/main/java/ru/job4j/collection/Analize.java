package ru.job4j.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Map<Integer, User> users = new HashMap<>();
        int added = 0;
        int changed = 0;
        int deleted;
        for (User user : previous) {
            users.put(user.id, user);
        }
        for (User user : current) {
            if (users.get(user.id) == null) {
                added++;
            } else {
                if (!users.get(user.id).equals(user)) {
                    changed++;
                }
            }
        }
        deleted = previous.size() - current.size() + added;
        return new Info(added, changed, deleted);
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
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
            return id == user.id
                    && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }


        public static class Info {

        int added;
        int changed;
        int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }
    }

}
