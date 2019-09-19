package ru.job4j.logic;

import ru.job4j.models.UserAlreadyExists;
import ru.job4j.models.UserDoesNotExist;

import java.util.Map;

public interface Validate<T> {
    void add(T model) throws UserAlreadyExists;
    void update(T model) throws UserDoesNotExist, UserAlreadyExists;
    void delete(T user) throws UserDoesNotExist;
    Map<Integer, T> findAll();
    T findByID(int id) throws UserDoesNotExist;
    T isCredentional(String login, String password);
}
