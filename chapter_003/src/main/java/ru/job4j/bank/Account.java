package ru.job4j.bank;

public class Account {
    private Double value;
    private String requisites;

    public Account(Double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public Double getValue() {
        return value;
    }

    public String getRequisites() {
        return requisites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account account = (Account) o;

        if (value != null ? !value.equals(account.value) : account.value != null) {
            return false;
        }
        return requisites != null ? requisites.equals(account.requisites) : account.requisites == null;
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (requisites != null ? requisites.hashCode() : 0);
        return result;
    }
}
