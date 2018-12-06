package ru.job4j.nonblocking;

import java.util.concurrent.atomic.AtomicInteger;

public class Base {

    private final int id;
    private final AtomicInteger version;
    private final String name;

    public Base(int id, String name) {
        this.id = id;
        this.version = new AtomicInteger(0);
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public AtomicInteger getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }
}
