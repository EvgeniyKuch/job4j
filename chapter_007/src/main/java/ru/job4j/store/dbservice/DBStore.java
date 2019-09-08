package ru.job4j.store.dbservice;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.models.User;
import ru.job4j.store.Store;
import ru.job4j.store.dbservice.dao.UsersDAO;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DBStore implements Store<User> {

    private static final Logger LOG = LogManager.getLogger(DBStore.class.getName());

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DBStore INSTANCE = new DBStore();

    private DBStore() {
        try (InputStream in = DBStore.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            SOURCE.setUrl(config.getProperty("url"));
            SOURCE.setUsername(config.getProperty("username"));
            SOURCE.setPassword(config.getProperty("password"));
            SOURCE.setMinIdle(5);
            SOURCE.setMaxIdle(10);
            SOURCE.setMaxOpenPreparedStatements(100);
            new UsersDAO(SOURCE).createScheme();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public static DBStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User model) {
        try {
            new UsersDAO(SOURCE).insertUser(model);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(User model) {
        try {
            new UsersDAO(SOURCE).updateUser(model);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(User model) {
        try {
            new UsersDAO(SOURCE).deleteUser(model);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public Map<Integer, User> findAll() {
        Map<Integer, User> userMap = new HashMap<>();
        try {
            userMap = new UsersDAO(SOURCE).findAllUsers();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return userMap;
    }

    @Override
    public User findByID(int id) {
        User result = new User(-1);
        try {
            result = new UsersDAO(SOURCE).findUserByID(id);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public User findByLoginPassword(String login, String password) {
        User result = new User(-1);
        try {
            result = new UsersDAO(SOURCE).isCredentional(login, password);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
}
