package ru.job4j.loop;

/**
 * Факториал.
 */
public class Factorial {

    /**
     * Calc возращает факториал числа.
     * @param n аргумент факториала.
     * @return значение факториала.
     */
    public int calc(int n) {
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}