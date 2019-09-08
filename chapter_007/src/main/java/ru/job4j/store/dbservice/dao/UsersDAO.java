package ru.job4j.store.dbservice.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.models.Role;
import ru.job4j.models.User;
import ru.job4j.store.dbservice.executor.Executor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UsersDAO {
    private final Executor executor;

    public UsersDAO(BasicDataSource source) {
        this.executor = new Executor(source);
    }

    public void insertUser(User user) throws SQLException {
        executor.execEdit(
                "INSERT INTO users (name, login, email, createDate, password, role_id)"
                        + " SELECT ?, ?, ?, ?, ?, id FROM roles WHERE rule = ?",
                st -> {
                    acceptStatementForEditing(st, user);
                    st.setString(6, user.getRole().getRule());
                }
        );
    }

    public void updateUser(User user) throws SQLException {
        executor.execEdit(
                "UPDATE users"
                        + " SET name = ?, login = ?, email = ?, createDate = ?, password = ?, role_id = ?"
                        + " WHERE id = ?;",
                st -> {
                    acceptStatementForEditing(st, user);
                    st.setInt(6, findRole(user.getRole().getRule()));
                    st.setInt(7, user.getId());
                }
        );
    }

    private int findRole(String rule) throws SQLException {
        return executor.execQuery(
                "SELECT id FROM roles WHERE rule = ?;",
                st -> st.setString(1, rule),
                rs -> rs.next() ? rs.getInt("id") : -1
        );
    }

    private void acceptStatementForEditing(PreparedStatement st, User user) throws SQLException {
        st.setString(1, user.getName());
        st.setString(2, user.getLogin());
        st.setString(3, user.getEmail());
        st.setTimestamp(4, new Timestamp(user.getCreateDate()));
        st.setString(5, user.getPassword());
    }

    public void deleteUser(User user) throws SQLException {
        executor.execEdit(
                "DELETE FROM users WHERE id = ?;",
                st -> st.setInt(1, user.getId())
        );
    }

    public Map<Integer, User> findAllUsers() throws SQLException {
        return executor.execQuery(
                "SELECT u.id, u.name, u.login, u.email, u.createDate, u.password, u.role_id, r.rule"
                        + " FROM users AS u"
                        + "     LEFT OUTER JOIN roles AS r"
                        + "     ON u.role_id=r.id"
                        + ";",
                st -> { },
                rs -> {
                    Map<Integer, User> mapUsers = new HashMap<>();
                    while (rs.next()) {
                        User user = userFromResultSet(rs);
                        mapUsers.put(user.getId(), user);
                    }
                    return mapUsers;
                }
        );
    }

    public User findUserByID(int id) throws SQLException {
        return executor.execQuery(
                "SELECT u.id, u.name, u.login, u.email, u.createDate, u.password, u.role_id, r.rule"
                        + " FROM users AS u"
                        + "     LEFT OUTER JOIN roles AS r"
                        + "     ON u.role_id=r.id"
                        + " WHERE u.id=?"
                        + ";",
                st -> st.setInt(1, id),
                this::userOrEmptyFromResultSet
        );
    }

    public void  createScheme() throws SQLException {
        executor.execEdit(
                "CREATE TABLE IF NOT EXISTS roles ("
                        + " id SERIAL   PRIMARY KEY,"
                        + " rule        VARCHAR(255)"
                        + ");",
                st -> { }
        );
        insertRoleIfNotExist("root");
        insertRoleIfNotExist("user");
        executor.execEdit(
                "CREATE TABLE IF NOT EXISTS users ("
                        + " id SERIAL   PRIMARY KEY,"
                        + " name        VARCHAR(255),"
                        + " login       VARCHAR(255),"
                        + " email       VARCHAR(255),"
                        + " createDate  TIMESTAMP,"
                        + " password    VARCHAR(255),"
                        + " role_id     INTEGER REFERENCES roles(id) NOT NULL"
                        + ");",
                st -> { }
        );
        insertAdminIfNotExist();
    }

    private void insertAdminIfNotExist() throws SQLException {
        if (!existAdmin()) {
            insertUser(new User("Eugene", "admin",
                    "evgeniy.kuchumov@gmail.com", new Date().getTime(),
                    "password", new Role("root"))
            );
        }
    }

    private boolean existAdmin() throws SQLException {
        return executor.execQuery(
                "SELECT 1"
                        + " FROM users AS u"
                        + "     LEFT OUTER JOIN roles AS r"
                        + "     ON u.role_id=r.id"
                        + " WHERE r.rule='root' AND u.login='admin'"
                        + ";",
                st -> { },
                ResultSet::next
        );
    }

    private void insertRole(Role role) throws SQLException {
        executor.execEdit(
                "INSERT INTO roles (rule) VALUES (?);",
                st -> st.setString(1, role.getRule())
        );
    }

    private void insertRoleIfNotExist(String rule) throws SQLException {
        if (!existRole(rule)) {
            insertRole(new Role(rule));
        }
    }

    private boolean existRole(String rule) throws SQLException {
        return executor.execQuery(
                "SELECT 1 FROM roles WHERE rule = ?;",
                st -> st.setString(1, rule),
                ResultSet::next
        );
    }

    public User isCredentional(String login, String password) throws SQLException {
        return executor.execQuery(
                "SELECT u.id, u.name, u.login, u.email, u.createDate, u.password, u.role_id, r.rule"
                        + " FROM users AS u"
                        + "     LEFT OUTER JOIN roles AS r"
                        + "     ON u.role_id=r.id"
                        + " WHERE login = ? AND password = ?"
                        + ";",
                st -> {
                    st.setString(1, login);
                    st.setString(2, password);
                },
                this::userOrEmptyFromResultSet
        );
    }

    private User userFromResultSet(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("login"),
                rs.getString("email"),
                rs.getTimestamp("createDate").getTime(),
                rs.getString("password"),
                new Role(rs.getInt("role_id"), rs.getString("rule"))
        );
    }

    private User userOrEmptyFromResultSet(ResultSet rs) throws SQLException {
        return !rs.next() ? new User(-1) : userFromResultSet(rs);
    }
}
