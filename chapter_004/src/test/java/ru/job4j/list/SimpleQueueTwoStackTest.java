package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleQueueTwoStackTest {

    @Test
    public void whenPushThreeElementsThenPoll3ElementFIFO() {
        SimpleQueueTwoStack<Integer> queue = new SimpleQueueTwoStack<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);

        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
        assertThat(queue.poll(), is(3));
    }
}