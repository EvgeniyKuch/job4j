package ru.job4j.vending;

import org.junit.Test;
import ru.job4j.intersection.Segments;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CoffeeTest {

    @Test
    public void whenChangeEquals15() {
        Coffee coffee = new Coffee(new int[]{1, 2, 5, 10});
        int[] expect = new int[]{10, 5};
        assertThat(coffee.changes(50, 35), is(expect));
    }

    @Test
    public void whenChangeEquals18() {
        Coffee coffee = new Coffee(new int[]{1, 2, 5, 10});
        int[] expect = new int[]{10, 5, 2, 1};
        assertThat(coffee.changes(50, 32), is(expect));
    }
}
