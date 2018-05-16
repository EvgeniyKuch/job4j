package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест методов класса Max.
 */
public class MaxTest {

    /**
     * Тест максимума из 2-х чисел.
      */
    @Test
    public void whenFirstLessSecond() {
        Max maxim = new Max();
        int result = maxim.max(1, 2);
        assertThat(result, is(2));
    }

    /**
     * Тест максимума из 3-х чисел.
     */
    @Test
    public void whenSecondMost() {
        Max maxim = new Max();
        int result = maxim.maxOfThreeNumbers(1, 3, 2);
        assertThat(result, is(3));
    }
}
