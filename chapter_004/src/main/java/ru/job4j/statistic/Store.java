package ru.job4j.statistic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {

    public Info diff(List<User> previoues, List<User> current) {
        Map<Integer, User> currentMap = new HashMap<>();
        for (User user : current) {
            currentMap.put(user.id, user);
        }
        int deleted = 0;
        int changed = 0;
        for (User user : previoues) {
            if (!currentMap.containsKey(user.id)) {
                deleted++;
            } else if (!currentMap.get(user.id).equals(user)) {
                changed++;
            }
        }
        int added = current.size() - (previoues.size() - deleted);
        return new Info(added, changed, deleted);
    }

    static class User {
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

            if (id != user.id) {
                return false;
            }
            return name != null ? name.equals(user.name) : user.name == null;
        }

        @Override
        public int hashCode() {
            int result = id;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }
    }
}
