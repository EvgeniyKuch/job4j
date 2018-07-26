package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleArrayIterableTest {

    @Test
    public void whenIterableThatForEachLoop() {
        SimpleArray<String> array = new SimpleArray<>(3);
        array.add("first");
        array.add("second");
        array.add("third");

        String result = "";
        for (String string : array) {
            result = String.join(" ", result, string);
        }

        String expect = " first second third";

        assertThat(result, is(expect));
    }
}