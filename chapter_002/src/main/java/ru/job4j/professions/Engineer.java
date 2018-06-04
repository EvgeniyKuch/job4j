package ru.job4j.professions;

public class Engineer extends Profession {

    public Engineer(String name) {
        super(name, "Engineer");
    }

    public House buildHouse(House home) {
        return home;
    }
}
