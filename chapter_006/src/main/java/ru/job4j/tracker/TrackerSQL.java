package ru.job4j.tracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TrackerSQL implements ITracker, AutoCloseable {

    private Connection connection;
    private static final Logger LOG = LogManager.getLogger(TrackerSQL.class.getName());

    public TrackerSQL() {
        this.init();
    }

    public TrackerSQL(Connection connection) {
        this.connection = connection;
    }

    private void init() {
        try (InputStream in = TrackerSQL.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            preInit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private void preInit() {
        try (Statement st = this.connection.createStatement()) {
            st.addBatch(
                    "CREATE TABLE IF NOT EXISTS roles ("
                            + " id SERIAL   PRIMARY KEY,"
                            + " name        VARCHAR(2000)"
                            + ");"
            );
            st.addBatch(
                    "CREATE TABLE IF NOT EXISTS rules ("
                            + " id SERIAL   PRIMARY KEY,"
                            + " rule        VARCHAR(2000)"
                            + ");"
            );
            st.addBatch(
                    "CREATE TABLE IF NOT EXISTS roles_rules_compose ("
                            + " id SERIAL   PRIMARY KEY,"
                            + " role_id     INTEGER REFERENCES roles(id),"
                            + " rule_id     INTEGER REFERENCES rules(id)"
                            + ");"
            );
            st.addBatch(
                    "CREATE TABLE IF NOT EXISTS users ("
                            + " id SERIAL   PRIMARY KEY,"
                            + " name        VARCHAR(2000),"
                            + " role_id     INTEGER REFERENCES roles(id)"
                            + ");"
            );
            st.addBatch(
                    "CREATE TABLE IF NOT EXISTS categories ("
                            + " id SERIAL   PRIMARY KEY,"
                            + " name        VARCHAR(2000)"
                            + ");"
            );
            st.addBatch(
                    "CREATE TABLE IF NOT EXISTS states ("
                            + " id SERIAL   PRIMARY KEY,"
                            + " name        VARCHAR(2000)"
                            + ");"
            );
            st.addBatch(
                    "CREATE TABLE IF NOT EXISTS items ("
                            + " id SERIAL   PRIMARY KEY,"
                            + " user_id     INTEGER REFERENCES users(id),"
                            + " category_id INTEGER REFERENCES categories(id),"
                            + " state_id    INTEGER REFERENCES states(id),"
                            + " name        VARCHAR(255),"
                            + " description VARCHAR(2000),"
                            + " created     TIMESTAMP"
                            + ");"
            );
            st.addBatch(
                    "CREATE TABLE IF NOT EXISTS comments ("
                            + " id SERIAL   PRIMARY KEY,"
                            + " comment     VARCHAR(2000),"
                            + " item_id     INTEGER REFERENCES items(id)"
                            + ");"
            );
            st.addBatch(
                    "CREATE TABLE IF NOT EXISTS attachs ("
                            + " id SERIAL   PRIMARY KEY,"
                            + " path_file   VARCHAR(2000),"
                            + " item_id     INTEGER REFERENCES items(id)"
                            + ");"
            );
            st.executeBatch();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private String edit(String sql, ConsumerEx<PreparedStatement> set) {
        String id = null;
        try (PreparedStatement st = connection.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            set.accept(st);
            st.executeUpdate();
            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    id = rs.getString("id");
                }
            }
            catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return id;
    }

    private List<Item> find(String sql, ConsumerEx<PreparedStatement> set) {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            set.accept(st);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Item item = new Item(
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getTimestamp("created").getTime()
                    );
                    item.setId(rs.getString("id"));
                    result.add(item);
                }
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Item add(Item item) {
        String id = edit(
                "INSERT INTO items(name, description, created) VALUES (?, ?, ?);",
                st -> {
                    st.setString(1, item.getName());
                    st.setString(2, item.getDesc());
                    st.setTimestamp(3, new Timestamp(item.getCreated()));
                });
        item.setId(id);
        return item;
    }

    @Override
    public void replace(String id, Item item) {
        edit("UPDATE items SET name = ?, description = ?, created = ? "
                        + "WHERE id = ?;",
                st -> {
                    st.setString(1, item.getName());
                    st.setString(2, item.getDesc());
                    st.setTimestamp(3, new Timestamp(item.getCreated()));
                    st.setInt(4, Integer.parseInt(id));
                });
    }

    @Override
    public void delete(String id) {
        edit("DELETE FROM items WHERE id = ?;",
                st -> st.setInt(1, Integer.parseInt(id)));
    }

    @Override
    public List<Item> findAll() {
        return find(
                "SELECT id, name, description, created FROM items;",
                st -> { });
    }

    @Override
    public List<Item> findByName(String key) {
        return find(
                "SELECT id, name, description, created "
                        + "FROM items WHERE name = ?;",
                st -> st.setString(1, key));
    }

    @Override
    public Item findById(String id) {
        List<Item> result = find(
                "SELECT id, name, description, created "
                        + "FROM items WHERE id = ?;",
                st -> st.setInt(1, Integer.parseInt(id)));
        return result.size() > 0 ? result.get(0) : null;
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            this.connection.close();
        }
    }

    public boolean isAvailable() throws SQLException {
        return this.connection != null && !this.connection.isClosed();
    }
}
