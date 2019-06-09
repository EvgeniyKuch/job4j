package ru.job4j.store;

import ru.job4j.models.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryStore implements Store {
    private static final MemoryStore INSTANCE = new MemoryStore();
    private final Map<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger(0);

    private MemoryStore() {
    }

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) {
        user.setId(id.incrementAndGet());
        users.put(user.getId(), user);
    }

    @Override
    public void update(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public void delete(User user) {
        users.remove(user.getId());
    }

    @Override
    public Map<Integer, User> findAll() {
        return users;
    }

    @Override
    public User findByID(int id) {
        return users.getOrDefault(id, new User(-1));
    }
}
