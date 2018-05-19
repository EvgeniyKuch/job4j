package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

public class TriangleTest {

    /**
     * Тест, когда треугольник можно пострить.
     */
    @Test
    public void whenAreaSetThreePointsThenTriangleArea() {
        // создаем три объекта класса Point.
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        Point c = new Point(2, 0);
        // Создаем объект треугольник и передаем в него объекты точек.
        Triangle triangle = new Triangle(a, b, c);
        // Вычисляем площадь.
        double result = triangle.area();
        // Задаем ожидаемый результат.
        double expected = 2D;
        //Проверяем результат и ожидаемое значение.
        assertThat(result, closeTo(expected, 0.1));
    }

    /**
     * Тест, когда точки лежат на одной прямой и треугольник пострить нельзя.
     */
    @Test
    public void whenThreePointsCollinearThenMinusOne() {
        // создаем три объекта класса Point.
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        Point c = new Point(0, 4);
        // Создаем объект треугольник и передаем в него объекты точек.
        Triangle triangle = new Triangle(a, b, c);
        // Вычисляем площадь.
        double result = triangle.area();
        // Задаем ожидаемый результат.
        double expected = -1D;
        //Проверяем результат и ожидаемое значение.
        assertThat(result, closeTo(expected, 0.1));
    }
}
