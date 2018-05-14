package ru.job4j.calculator;

/**
 *Класс Calculator представляет собой элементарный
 * калькулятор. Позволяет производить сложение, вычитание,
 * деление и умножение.
 *@author Kuchumov
 *@since 14.05.2018
 */
public class Calculator {
    /**
     *Результат вычислений.
     */
    private double result;

    /**
     * Сложение.
     * @param first - первое слагаемое.
     * @param second - второе слагаемое.
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Вычитание.
     * @param first - уменьшаемое.
     * @param second - вычитаемое.
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Деление.
     * @param first - делимое.
     * @param second - делитель.
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * Умножение.
     * @param first - первый множитель.
     * @param second - второй множитель.
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     * Получить результат.
     * @return - результат.
     */
    public double getResult() {
        return this.result;
    }
}
