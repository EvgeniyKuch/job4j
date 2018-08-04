package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleLinkedListDynamicsTest {

    private SimpleLinkedListDynamics<Integer> list;

    @Before
    public void setUp() {
        list = new SimpleLinkedListDynamics<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
    }

    @Test
    public void whenAddElementsThenGetElements() {
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenIndexLessZeroThenException() {
        list.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenIndexMoreMaxThenException() {
        list.get(4);
    }

    @Test
    public void whenIterableThenForEachLoop() {
        int sum = 0;
        for (int each : list) {
            sum += each;
        }
        assertThat(sum, is(10));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoSuchElementThenException() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenFailFastThenException() {
        Iterator<Integer> it = list.iterator();
        list.add(5);
        it.next();
    }
}