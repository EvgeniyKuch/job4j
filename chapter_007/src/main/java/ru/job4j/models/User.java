package ru.job4j.models;

import java.util.Date;
import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String login;
    private String email;
    private long createDate;
    private String password;
    private Role role;

    public User(int id) {
        this.id = id;
    }

    public User(int id, String name, String login, String email, long createDate, Role role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
        this.role = role;
    }

    public User(String name, String login, String email, long createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public User(int id, String name, String login, String email, long createDate, String pass, Role role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
        this.password = pass;
        this.role = role;
    }

    public User(String name, String login, String email, long createDate, String pass, Role role) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
        this.password = pass;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public long getCreateDate() {
        return createDate;
    }

    public String getDate() {
        return new Date(createDate).toString();
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id
                && createDate == user.createDate
                && Objects.equals(name, user.name)
                && Objects.equals(login, user.login)
                && Objects.equals(email, user.email)
                && Objects.equals(password, user.password)
                && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, login, email, createDate, password, role);
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", login='" + login + '\''
                + ", email='" + email + '\''
                + ", createDate=" + new Date(createDate)
                + '}';
    }
}
