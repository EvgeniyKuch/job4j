package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleArrayListDynamicsTest {

    private SimpleArrayListDynamics<Integer> arr;

    @Before
    public void setUp() {
        arr = new SimpleArrayListDynamics<>();
        for (int i = 0; i != 12; i++) {
            arr.add(i);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddTwelveElementsThenUseGetFiveResultFive() {
        assertThat(arr.get(5), is(5));
        arr.get(12);
    }

    @Test
    public void whenIterableThenForEachLoop() {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        assertThat(sum, is(66));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenFailFastThenException() {
        Iterator<Integer> it = arr.iterator();
        arr.add(12);
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoSuchElementThenException() {
        Iterator<Integer> it = arr.iterator();
        for (int i = 0; i != 13; i++) {
            it.next();
        }
    }
}