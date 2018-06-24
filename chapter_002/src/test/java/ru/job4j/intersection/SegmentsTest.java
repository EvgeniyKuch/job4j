package ru.job4j.intersection;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SegmentsTest {

    @Test
    public void whenSegmentsDoNotIntersectABCD() {
        Segments segments = new Segments(1, 2, 3, 4);
        assertThat(segments.intersection(), is(false));
    }

    @Test
    public void whenSegmentsDoNotIntersectCDAB() {
        Segments segments = new Segments(3, 4, 1, 2);
        assertThat(segments.intersection(), is(false));
    }

    @Test
    public void whenSegmentsIntersectACBD() {
        Segments segments = new Segments(1, 3, 2, 4);
        assertThat(segments.intersection(), is(true));
    }

    @Test
    public void whenSegmentsIntersectCADB() {
        Segments segments = new Segments(2, 4, 1, 3);
        assertThat(segments.intersection(), is(true));
    }

    @Test
    public void whenSegmentsAreTheSame() {
        Segments segments = new Segments(2, 5, 2, 5);
        assertThat(segments.intersection(), is(true));
    }
}
