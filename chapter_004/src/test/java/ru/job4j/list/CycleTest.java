package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CycleTest {

    private Cycle cycle = new Cycle();
    private Cycle.Node<Integer> first;
    private Cycle.Node<Integer> two;
    private Cycle.Node<Integer> third;
    private Cycle.Node<Integer> four;

    @Before
    public void setUp() {
        cycle = new Cycle();
        first = new Cycle().new Node<>(1);
        two = new Cycle().new Node<>(2);
        third = new Cycle().new Node<>(3);
        four = new Cycle().new Node<>(4);
    }

    @Test
    public void whenFirstElementClosesToFourthThenTruth() {

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;

        assertThat(cycle.hasCycle(first), is(true));
    }

    @Test
    public void whenThirdElementClosesToSecondThenTruth() {

        first.next = two;
        two.next = third;
        third.next = two;
        four.next = null;

        assertThat(cycle.hasCycle(first), is(true));
    }

    @Test
    public void whenLinkedListIsNotClosedThenFalse() {

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = null;

        assertThat(cycle.hasCycle(first), is(false));
    }
}