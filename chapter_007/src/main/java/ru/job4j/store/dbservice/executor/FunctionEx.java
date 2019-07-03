package ru.job4j.store.dbservice.executor;

import java.sql.SQLException;

@FunctionalInterface
public interface FunctionEx<T, R> {

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    R apply(T t) throws SQLException;
}
