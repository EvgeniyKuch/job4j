package ru.job4j.store.dbservice.executor;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.tracker.ConsumerEx;

import java.sql.*;

public class Executor {
    private final BasicDataSource source;

    public Executor(BasicDataSource source) {
        this.source = source;
    }

    public void execEdit(String edit, ConsumerEx<PreparedStatement> set)
            throws SQLException {
        try (Connection connection = source.getConnection();
             PreparedStatement st = connection.prepareStatement(edit)
        ) {
            set.accept(st);
            st.executeUpdate();
        }
    }

    public <T> T execQuery(String query, ConsumerEx<PreparedStatement> set,
                           FunctionEx<ResultSet, T> handler)
            throws SQLException {
        try (Connection connection = source.getConnection();
             PreparedStatement st = connection.prepareStatement(query)
        ) {
            set.accept(st);
            T result;
            try (ResultSet rs = st.executeQuery()) {
                result = handler.apply(rs);
            }
            return result;
        }
    }
}

