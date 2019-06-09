package ru.job4j.models;

public class UserDoesNotExist extends Exception {
    public UserDoesNotExist() {
        super("User does not exist");
    }
}
