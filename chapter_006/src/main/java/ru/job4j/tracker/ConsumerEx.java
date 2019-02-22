package ru.job4j.tracker;

import java.sql.SQLException;

@FunctionalInterface
public interface ConsumerEx<T> {

    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     */
    void accept(T t) throws SQLException;
}
