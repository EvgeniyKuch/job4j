package ru.job4j.models;

public class UserAlreadyExists extends Exception {
    public UserAlreadyExists() {
        super("User already exists");
    }
}
