package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    private SimpleArray<String> array;

    @Before
    public void createSimpleArray() {
        array = new SimpleArray<>(2);
    }

    @Test(expected = SimpleArrayOverflowException.class)
    public void whenAddStringThenContainString() {
        array.add("first");
        array.add("second");

        assertThat(array.get(0), is("first"));
        assertThat(array.get(1), is("second"));
        array.add("third");
    }

    @Test(expected = IllegalIndexException.class)
    public void whenSetStringThatNewValue() {
        array.add("first");
        array.add("second");
        array.set(1, "reset");

        assertThat(array.get(1), is("reset"));
        array.set(3, "exception");
    }

    @Test(expected = IllegalIndexException.class)
    public void whenDeleteStringThatDeleteItem() {
        array.add("first");
        array.add("second");
        array.delete(1);

        assertThat(array.get(0), is("first"));
        assertThat(array.get(1), is(""));
    }
}