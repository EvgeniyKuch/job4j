package ru.job4j.set;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleLinkedSetTest {

    @Test
    public void whenAddDuplicateThenNotDuplicate() {
        SimpleLinkedSet<Integer> set = new SimpleLinkedSet<>();
        assertThat(set.add(1), is(true));
        assertThat(set.add(2), is(true));
        assertThat(set.add(2), is(false));
        assertThat(set.add(3), is(true));

        int count = 0;
        int sum = 0;
        for (int each : set) {
            count++;
            sum += each;
        }

        assertThat(count, is(3));
        assertThat(sum, is(6));
    }

}