package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

public class PointTest {

    @Test
    public void whenPointsCoordinatesThreeZeroAndZeroFourThenFive() {
        Point a = new Point(3, 0);
        Point b = new Point(0, 4);
        double result = a.distanceTo(b);
        assertThat(result, closeTo(5D, 0.1));
    }
}
