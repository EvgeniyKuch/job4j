package ru.job4j.pseudo;

import org.junit.Test;

import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SquareTest {

    @Test
    public void whenDrawSquare() {
        Shape shape = new Square();
        assertThat(
                shape.draw(),
                is(
                        new StringJoiner(
                                System.lineSeparator(), "",
                                System.lineSeparator())
                                .add("++++")
                                .add("++++")
                                .add("++++")
                                .add("++++")
                                .toString()
                )
        );
    }
}
