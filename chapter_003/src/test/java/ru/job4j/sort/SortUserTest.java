package ru.job4j.sort;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {

    @Test
    public void whenListUserThenSetSortedByAge(){
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
}
