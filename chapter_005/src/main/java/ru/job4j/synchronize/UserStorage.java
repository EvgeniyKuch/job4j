package ru.job4j.synchronize;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private final Map<Integer, User> storage = new HashMap<>();

    public synchronized boolean add(User user) {
        return this.storage.putIfAbsent(user.getId(), user) == null;
    }

    public synchronized boolean update(User user) {
        return this.storage.computeIfPresent(user.getId(), (k, v) -> user) != null;
    }

    public synchronized boolean delete(User user) {
        return this.storage.remove(user.getId()) != null;
    }

    public synchronized User getUser(int id) {
        return this.storage.get(id);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        if (this.storage.containsKey(fromId)
                && this.storage.containsKey(toId)
                && this.storage.get(fromId).getAmount() >= amount
                ) {
            User newFrom = new User(fromId,
                    this.storage.get(fromId).getAmount() - amount);
            User newTo = new User(toId,
                    this.storage.get(toId).getAmount() + amount);
            this.update(newFrom);
            this.update(newTo);
            result = true;
        }
        return result;
    }
}
