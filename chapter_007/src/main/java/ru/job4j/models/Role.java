package ru.job4j.models;

import java.util.Objects;

public class Role {

    private int id;
    private String rule;

    public Role(int id, String rule) {
        this.id = id;
        this.rule = rule;
    }

    public Role(String rule) {
        this.rule = rule;
    }

    public Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return id == role.id
                && Objects.equals(rule, role.rule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rule);
    }
}
