package ru.job4j.pseudo;

import org.junit.Test;

import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TriangleTest {

    @Test
    public void whenDrawTriangle() {
        Shape shape = new Triangle();
        assertThat(
                shape.draw(),
                is(
                        new StringJoiner(
                                System.lineSeparator(), "",
                                System.lineSeparator())
                                .add("    +    ")
                                .add("   +++   ")
                                .add("  +++++  ")
                                .add(" +++++++ ")
                                .add("+++++++++")
                                .toString()
                )
        );
    }
}
