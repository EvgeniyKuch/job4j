package ru.job4j.tracker;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class TrackerSQLTest {

    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void checkConnection() throws SQLException {
        TrackerSQL tracker;
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            tracker = trackerSQL;
            assertThat(trackerSQL.isAvailable(), is(true));
        }
        assertThat(tracker.isAvailable(), is(false));
    }

    @Test
    public void whenAddThanFindById() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            String id = tracker.add(new Item("Item1", "Desc1")).getId();
            assertThat(tracker.findById(id).getName(), is("Item1"));
        }
    }

    @Test
    public void whenAddThanFindByName() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("Item1", "Desc1"));
            assertThat(tracker.findByName("Item1").size(), is(1));
        }
    }

    @Test
    public void whenAddTwoItemsThanFindAll() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("Item1", "Desc1"));
            tracker.add(new Item("Item2", "Desc2"));
            assertThat(tracker.findAll().size(), is(2));
        }
    }

    @Test
    public void whenDeleteThanNotFind() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            String id = tracker.add(new Item("Item1", "Desc1")).getId();
            tracker.delete(id);
            assertThat(tracker.findById(id), nullValue());
            assertThat(tracker.findByName("Item1").size(), is(0));
            assertThat(tracker.findAll().size(), is(0));
        }
    }

    @Test
    public void whenReplaceThanNew() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            String id = tracker.add(new Item("Item1", "Desc1")).getId();
            tracker.replace(id, new Item("ItemNew1", "DescNew1"));
            assertThat(tracker.findById(id).getName(), is("ItemNew1"));
        }
    }
}