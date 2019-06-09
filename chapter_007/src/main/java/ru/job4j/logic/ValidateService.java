package ru.job4j.logic;

import ru.job4j.models.User;
import ru.job4j.models.UserAlreadyExists;
import ru.job4j.models.UserDoesNotExist;
import ru.job4j.store.MemoryStore;
import ru.job4j.store.Store;

import java.util.Map;

public class ValidateService {
    private static final ValidateService INSTANCE = new ValidateService();
    private final Store store = MemoryStore.getInstance();

    private ValidateService() {

    }

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    public void add(User user) throws UserAlreadyExists {
        findUser(user);
        store.add(user);
    }

    public void update(User user) throws UserDoesNotExist, UserAlreadyExists {
        findByID(user.getId());
        findUser(user);
        store.update(user);
    }

    public void delete(User user) throws UserDoesNotExist {
        findByID(user.getId());
        store.delete(user);
    }

    public Map<Integer, User> findAll() {
        return store.findAll();
    }

    public User findByID(int id) throws UserDoesNotExist {
        User result = store.findByID(id);
        if (result.getId() == -1) {
            throw new UserDoesNotExist();
        }
        return result;
    }

    private void findUser(User user) throws UserAlreadyExists {
        if (store.findAll().containsValue(user)) {
            throw new UserAlreadyExists();
        }
    }
}
