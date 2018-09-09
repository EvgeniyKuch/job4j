package ru.job4j.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TreeTest {

    private Tree<Integer> tree;

    @Before
    public void setUp() {
        tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
    }

    @Test
    public void when6ElFindLastThen6() {
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void whenAddDuplicateThenFalse() {
        assertThat(tree.add(1, 6), is(false));
    }

    @Test
    public void whenParentNotFoundThenFalse() {
        assertThat(tree.add(7, 8), is(false));
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenIterableThenForEachLoop() {
        int sum = 0;
        for (int el : tree) {
            sum += el;
        }
        assertThat(sum, is(21));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenModificationAfterIteratorThenException() {
        Iterator<Integer> itr = tree.iterator();
        tree.add(6, 7);
        itr.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoSuchElementThenException() {
        Tree<Integer> tree = new Tree<>(1);
        Iterator<Integer> iterator = tree.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        iterator.next();
    }

    @Test
    public void whenTreeIsBinaryThenTrue() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        tree.add(3, 7);
        assertThat(tree.isBinary(), is(true));
    }

    @Test
    public void whenTreeIsNotBinaryThenFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(2, 6);
        tree.add(3, 7);
        tree.add(3, 8);
        assertThat(tree.isBinary(), is(false));
    }
}