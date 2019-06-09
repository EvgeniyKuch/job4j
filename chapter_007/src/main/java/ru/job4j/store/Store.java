package ru.job4j.store;

import ru.job4j.models.User;

import java.util.Map;

public interface Store {
    void add(User user);
    void update(User user);
    void delete(User user);
    Map<Integer, User> findAll();
    User findByID(int id);
}
