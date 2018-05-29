package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестирование методов класса ru.job4j.array.Merging.
 */
public class MergingTest {

    /**
     * Тест метода ru.job4j.array.Merging.arraysMerging.
     */
    @Test
    public void whenTwoArraysOrderedAscendingThenOneArrayOrderedAscending() {
        int[] first = {1, 3, 5, 7};
        int[] second = {2, 4, 6, 8, 10, 11, 12};
        Merging arr = new Merging();
        int[] rst = arr.arraysMerging(first, second);
        int[] expect = {1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12};
        assertThat(rst, is(expect));
    }
}
