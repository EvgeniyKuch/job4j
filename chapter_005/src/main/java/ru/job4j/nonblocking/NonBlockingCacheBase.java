package ru.job4j.nonblocking;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NonBlockingCacheBase {

    private Map<Integer, Base> cache = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return cache.putIfAbsent(model.getId(), model) == null;
    }

    public boolean delete(Base model) {
        return cache.remove(model.getId()) != null;
    }

    public boolean update(Base newModel) {
        return cache.computeIfPresent(newModel.getId(),
                (k, oldModel) -> {
                    int old = oldModel.getVersion().get();
                    newModel.getVersion().set(old + 1);
                    try {
                        Thread.sleep(75);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!oldModel.getVersion().compareAndSet(old, old + 1)) {
                        throw new OptimisticException();
                    }
                    return newModel;
                }) != null;
    }

    public Base get(int id) {
        return cache.get(id);
    }
}
