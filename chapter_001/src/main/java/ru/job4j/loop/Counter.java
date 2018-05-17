package ru.job4j.loop;

/**
 * Counter.
 */
public class Counter {

    /**
     * Подсчет суммы чётных чисел в диапазоне.
     * @param start - начало диапазона.
     * @param finish - конец диапазона.
     * @return - сумма чётных чисел в диапазоне.
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}
