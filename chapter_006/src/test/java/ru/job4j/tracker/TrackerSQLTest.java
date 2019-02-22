package ru.job4j.tracker;

import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class TrackerSQLTest {

    @Test
    public void checkConnection() throws SQLException {
        TrackerSQL tracker;
        try (TrackerSQL trackerSQL = new TrackerSQL()) {
            tracker = trackerSQL;
            assertThat(trackerSQL.isAvailable(), is(true));
        }
        assertThat(tracker.isAvailable(), is(false));
    }

    @Test
    public void whenAddThanFind() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL()) {
            String id = tracker.add(new Item("Item1", "Desc1")).getId();
            assertThat(tracker.findById(id).getName(), is("Item1"));
        }
    }

    @Test
    public void whenDeleteThanNotFind() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL()) {
            String id = tracker.add(new Item("Item1", "Desc1")).getId();
            tracker.delete(id);
            assertThat(tracker.findById(id), nullValue());
        }
    }

    @Test
    public void whenReplaceThanNew() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL()) {
            String id = tracker.add(new Item("Item1", "Desc1")).getId();
            tracker.replace(id, new Item("ItemNew1", "DescNew1"));
            assertThat(tracker.findById(id).getName(), is("ItemNew1"));
        }
    }
}