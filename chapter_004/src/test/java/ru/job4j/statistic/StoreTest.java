package ru.job4j.statistic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StoreTest {

    @Test
    public void whenAdded1Deleted2Changed1ThenInfo() {
        List<Store.User> previoues = new ArrayList<>();
        previoues.add(new Store.User(1, "Eugen"));
        previoues.add(new Store.User(2, "Pavel"));
        previoues.add(new Store.User(3, "Vital"));
        previoues.add(new Store.User(4, "Nina"));
        previoues.add(new Store.User(5, "Natasha"));

        //удалили id 2 и 5, изменили id 3, добавили id 6
        List<Store.User> current = new ArrayList<>();
        current.add(new Store.User(1, "Eugen"));
        current.add(new Store.User(3, "Kristina"));
        current.add(new Store.User(4, "Nina"));
        current.add(new Store.User(6, "Irina"));
        Collections.shuffle(current);

        Store store = new Store();
        Info expect = new Info(1, 1, 2);
        assertThat(store.diff(previoues, current), is(expect));
    }
}