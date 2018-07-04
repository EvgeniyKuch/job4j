package ru.job4j.sort;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {

    @Test
    public void whenListUserThenSetSortedByAge() {
        List<User> list = new ArrayList<>(Arrays.asList(
                new User("Ivan", 85),
                new User("Vladimir", 16),
                new User("Pavel", 46),
                new User("Igor", 38)
        ));
        SortUser sortUser = new SortUser();
        Set<User> result = sortUser.sort(list);
        Set<User> expect = new LinkedHashSet<>();
        expect.add(new User("Vladimir", 16));
        expect.add(new User("Igor", 38));
        expect.add(new User("Pavel", 46));
        expect.add(new User("Ivan", 85));
        assertThat(result, is(expect));
    }

    @Test
    public void whenListUserThenListSortedByNameLength() {
        List<User> list = new ArrayList<>(Arrays.asList(
                new User("Vladimir", 16),
                new User("Ivan", 85),
                new User("Eugene", 38),
                new User("Pavel", 46)
        ));
        SortUser sortUser = new SortUser();
        List<User> result = sortUser.sortNameLength(list);
        List<User> expect = new ArrayList<>(Arrays.asList(
                new User("Ivan", 85),
                new User("Pavel", 46),
                new User("Eugene", 38),
                new User("Vladimir", 16)
        ));
        assertThat(result, is(expect));
    }

    @Test
    public void whenListUserThenListSortedByAllFields() {
        List<User> list = new ArrayList<>(Arrays.asList(
                new User("Сергей", 25),
                new User("Иван", 30),
                new User("Сергей", 20),
                new User("Иван", 25)
        ));
        SortUser sortUser = new SortUser();
        List<User> result = sortUser.sortByAllFields(list);
        List<User> expect = new ArrayList<>(Arrays.asList(
                new User("Иван", 25),
                new User("Иван", 30),
                new User("Сергей", 20),
                new User("Сергей", 25)
        ));
        assertThat(result, is(expect));
    }
}
