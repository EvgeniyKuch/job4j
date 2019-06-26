package ru.job4j.store;

import java.util.Map;

public interface Store<T> {
    void add(T model);
    void update(T model);
    void delete(T model);
    Map<Integer, T> findAll();
    T findByID(int id);
}
