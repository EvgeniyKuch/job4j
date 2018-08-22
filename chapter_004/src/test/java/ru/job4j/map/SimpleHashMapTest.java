package ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class SimpleHashMapTest {

    @Test
    public void whenAddTwoElementsThenGetTwoElements() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("One", 1);
        map.insert("Two", 2);
        assertThat(map.get("One"), is(1));
        assertThat(map.get("Two"), is(2));
    }

    @Test
    public void whenAddTenElementsThenResizeAndIteratorGetTenElements() {
        SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>();
        for (int i = 1; i <= 10; i++) {
            map.insert(i, 2 * i);
        }
        Iterator<Integer> itrKey = map.iteratorKeys();
        Iterator<Integer> itrVal = map.iteratorValues();
        int sumKey = 0;
        while (itrKey.hasNext()) {
            sumKey += itrKey.next();
        }
        int sumVal = 0;
        while (itrVal.hasNext()) {
            sumVal += itrVal.next();
        }
        assertThat(sumKey, is(55));
        assertThat(sumVal, is(110));
    }

    @Test
    public void whenDeleteElementThenNotFound() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("One", 1);
        assertThat(map.delete("One"), is(true));
        assertThat(map.delete("Two"), is(false));
        assertThat(map.get("One"), is(nullValue()));
    }

    @Test
    public void whenAddTwoPairsWithSomeKeysThenJustSecondValue() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("One", 1);
        map.insert("One", 2);
        assertThat(map.get("One"), is(2));
    }

    @Test
    public void whenCollisionThenFalse() {
        SimpleHashMap<StringHash, Integer> map = new SimpleHashMap<>();
        assertThat(map.insert(new StringHash("One"), 1), is(true));
        assertThat(map.insert(new StringHash("One"), 2), is(false));
    }

    private class StringHash {
        private String str;

        public StringHash(String str) {
            this.str = str;
        }

        @Override
        public int hashCode() {
            return str != null ? str.hashCode() : 0;
        }
    }
}