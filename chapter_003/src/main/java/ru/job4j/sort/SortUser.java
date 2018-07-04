package ru.job4j.sort;

import java.util.*;

public class SortUser {

    /**
     * Сортировка по возрасту, в естественном порядке,
     * исходя из Comparable.compareTo в классе User.
     * @param listUser список пользователей.
     * @return отсортированное по возрасту множество.
     */
    public Set<User> sort(List<User> listUser) {
        return new TreeSet<>(listUser);
    }

    /**
     * Сортировка по длине имени.
     * @param listUser список пользователей.
     * @return отсортированный по длине имени список.
     */
    public List<User> sortNameLength(List<User> listUser) {
        Collections.sort(listUser, Comparator.comparingInt(user -> user.getName().length()));
        return listUser;
    }

    /**
     * Сортировка сначала по имени в лексикографическом порядке, потом по возрасту.
     * @param listUser список пользователей.
     * @return отсортированный список.
     */
    public List<User> sortByAllFields(List<User> listUser) {
        Collections.sort(listUser, Comparator.comparing(User::getName).thenComparingInt(User::getAge));
        return listUser;
    }
}
