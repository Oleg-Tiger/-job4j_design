package ru.job4j.collection;

import java.util.List;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        int added;
        int changed = 0;
        int deleted = 0;
        for (User user : previous) {
            int index = current.indexOf(user);
            if (index == -1) {
                deleted++;
            } else {
                if (!user.name.equals(current.get(index).name)) {
                    changed++;
                }
            }
        }
        added = current.size() + deleted - previous.size();
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
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(id);
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
