package ru.job4j.logic;

import ru.job4j.models.User;
import ru.job4j.models.UserAlreadyExists;
import ru.job4j.models.UserDoesNotExist;

import java.util.HashMap;
import java.util.Map;

public class ValidateStub implements Validate<User> {
    private final Map<Integer, User> store = new HashMap<>();
    private int ids = 1;

    @Override
    public void add(User user) throws UserAlreadyExists {
        user.setId(this.ids++);
        this.store.put(user.getId(), user);
    }

    @Override
    public void update(User user) throws UserDoesNotExist, UserAlreadyExists {
        this.store.put(user.getId(), user);
    }

    @Override
    public void delete(User user) throws UserDoesNotExist {
        this.store.remove(user.getId());
    }

    @Override
    public Map<Integer, User> findAll() {
        return store;
    }

    @Override
    public User findByID(int id) throws UserDoesNotExist {
        return store.getOrDefault(id, new User(-1));
    }

    @Override
    public User isCredentional(String login, String password) {
        User result = new User(-1);
        for (User user : this.store.values()) {
            if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                result = user;
                break;
            }
        }
        return result;
    }
}
