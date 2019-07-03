package ru.job4j.store.dbservice.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.models.User;
import ru.job4j.store.dbservice.executor.Executor;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class UsersDAO {
    private final Executor executor;

    public UsersDAO(BasicDataSource source) {
        this.executor = new Executor(source);
    }

    public void insertUser(User user) throws SQLException {
        executor.execEdit(
                "INSERT INTO users (name, login, email, createDate) VALUES (?, ?, ?, ?);",
                st -> {
                    st.setString(1, user.getName());
                    st.setString(2, user.getLogin());
                    st.setString(3, user.getEmail());
                    st.setTimestamp(4, new Timestamp(user.getCreateDate()));
                }
        );
    }

    public void updateUser(User user) throws SQLException {
        executor.execEdit(
                "UPDATE users"
                        + " SET name = ?, login = ?, email = ?, createDate = ?"
                        + " WHERE id = ?;",
                st -> {
                    st.setString(1, user.getName());
                    st.setString(2, user.getLogin());
                    st.setString(3, user.getEmail());
                    st.setTimestamp(4, new Timestamp(user.getCreateDate()));
                    st.setInt(5, user.getId());
                }
        );
    }

    public void deleteUser(User user) throws SQLException {
        executor.execEdit(
                "DELETE FROM users WHERE id = ?;",
                st -> st.setInt(1, user.getId())
        );
    }

    public Map<Integer, User> findAllUsers() throws SQLException {
        return executor.execQuery(
                "SELECT id, name, login, email, createDate FROM users;",
                st -> { },
                rs -> {
                    Map<Integer, User> mapUsers = new HashMap<>();
                    while (rs.next()) {
                        User user = new User(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("login"),
                                rs.getString("email"),
                                rs.getTimestamp("createDate").getTime()
                        );
                        mapUsers.put(user.getId(), user);
                    }
                    return mapUsers;
                }
        );
    }

    public User findUserByID(int id) throws SQLException {
        return executor.execQuery(
                "SELECT id, name, login, email, createDate FROM users WHERE id = ?;",
                st -> st.setInt(1, id),
                rs -> !rs.next() ? new User(-1)
                        : new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("email"),
                        rs.getTimestamp("createDate").getTime()
                )
        );
    }

    public void  createTable() throws SQLException {
        executor.execEdit(
                "CREATE TABLE IF NOT EXISTS users ("
                        + " id SERIAL   PRIMARY KEY,"
                        + " name        VARCHAR(255),"
                        + " login       VARCHAR(255),"
                        + " email       VARCHAR(255),"
                        + " createDate  TIMESTAMP"
                        + ");",
                st -> { }
        );
    }
}
