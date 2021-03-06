package ru.job4j.logic;

import ru.job4j.models.User;
import ru.job4j.models.UserAlreadyExists;
import ru.job4j.models.UserDoesNotExist;
import ru.job4j.store.MemoryStore;
import ru.job4j.store.Store;
import ru.job4j.store.dbservice.DBStore;

import java.util.Map;

public class ValidateService implements Validate<User> {
    private static final ValidateService INSTANCE = new ValidateService();
    private final Store<User> store = DBStore.getInstance();

    private ValidateService() {

    }

    public static Validate<User> getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) throws UserAlreadyExists {
        findUser(user);
        store.add(user);
    }

    @Override
    public void update(User user) throws UserDoesNotExist, UserAlreadyExists {
        findByID(user.getId());
        findUser(user);
        store.update(user);
    }

    @Override
    public void delete(User user) throws UserDoesNotExist {
        findByID(user.getId());
        store.delete(user);
    }

    @Override
    public Map<Integer, User> findAll() {
        return store.findAll();
    }

    @Override
    public User findByID(int id) throws UserDoesNotExist {
        User result = store.findByID(id);
        if (result.getId() == -1) {
            throw new UserDoesNotExist();
        }
        return result;
    }

    @Override
    public User isCredentional(String login, String password) {
        return store.findByLoginPassword(login, password);
    }

    private void findUser(User user) throws UserAlreadyExists {
        if (store.findAll().containsValue(user)) {
            throw new UserAlreadyExists();
        }
    }
}
