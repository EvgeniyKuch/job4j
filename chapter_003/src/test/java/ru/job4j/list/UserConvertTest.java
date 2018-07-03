package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {

    @Test
    public void whenUserConvertToMap() {
        List<User> list = list = Arrays.asList(
                new User(1546, "Ivan", "Ivanovo"),
                new User(7987, "Vladimir", "Moscow"),
                new User(1321, "Nikolay", "St. Petersburg")
                );
        HashMap<Integer, User> expect = new HashMap<>();
        expect.put(1546, new User(1546, "Ivan", "Ivanovo"));
        expect.put(7987, new User(7987, "Vladimir", "Moscow"));
        expect.put(1321, new User(1321, "Nikolay", "St. Petersburg"));
        UserConvert userConvert = new UserConvert();
        HashMap<Integer, User> result = userConvert.process(list);
        assertThat(result, is(expect));
    }
}
