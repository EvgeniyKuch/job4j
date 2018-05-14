package ru.job4j.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест калькулятора.
 * @author Evgeniy Kuchumov (geka.1985@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class CalculatorTest {

    /**
     * Тест сложения.
     */
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    /**
     * Тест вычитания.
     */
    @Test
    public void whenSubTwoMinusOneThenOne() {
        Calculator calc = new Calculator();
        calc.subtract(2D, 1D);
        double result = calc.getResult();
        double expected = 1D;
        assertThat(result, is(expected));
    }

    /**
     * Тест деления.
     */
    @Test
    public void whenDivSixByTwoThenThree() {
        Calculator calc = new Calculator();
        calc.div(6D, 2D);
        double result = calc.getResult();
        double expected = 3D;
        assertThat(result, is(expected));
    }

    /**
     * Тест умножения.
     */
    @Test
    public void whenMulThreeByTwoThenSix() {
        Calculator calc = new Calculator();
        calc.multiple(3D, 2D);
        double result = calc.getResult();
        double expected = 6D;
        assertThat(result, is(expected));
    }
}
