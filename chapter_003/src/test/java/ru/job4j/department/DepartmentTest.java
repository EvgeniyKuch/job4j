package ru.job4j.department;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.*;

public class DepartmentTest {

    @Test
    public void whenSortByAscending() {
        List<String> list = new ArrayList<>(Arrays.asList(
                "K1\\SK1",
                "K1\\SK2",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K2",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"
        ));

        List<String> expect = new ArrayList<>();
        expect.add("K1");
        expect.add("K1\\SK1");
        expect.add("K1\\SK1\\SSK1");
        expect.add("K1\\SK1\\SSK2");
        expect.add("K1\\SK2");
        expect.add("K2");
        expect.add("K2\\SK1");
        expect.add("K2\\SK1\\SSK1");
        expect.add("K2\\SK1\\SSK2");

        List<String> result = new Department().sortAsc(list);
        assertThat(result, is(expect));
    }

    @Test
    public void whenSortByDescending() {
        List<String> list = new ArrayList<>(Arrays.asList(
                "K1\\SK1",
                "K1\\SK2",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K2",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"
        ));

        List<String> expect = new ArrayList<>();
        expect.add("K2");
        expect.add("K2\\SK1");
        expect.add("K2\\SK1\\SSK2");
        expect.add("K2\\SK1\\SSK1");
        expect.add("K1");
        expect.add("K1\\SK2");
        expect.add("K1\\SK1");
        expect.add("K1\\SK1\\SSK2");
        expect.add("K1\\SK1\\SSK1");

        List<String> result = new Department().sortDesc(list);
        assertThat(result, is(expect));
    }
}
