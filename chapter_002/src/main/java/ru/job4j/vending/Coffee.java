package ru.job4j.vending;

import java.util.Arrays;

/**
 * Кофемашина.
 */
public class Coffee {
    private int[] nominals;

    /**
     * Кофемашина.
     * @param nominals номиналы монет в автомате для сдачи.
     *                 Должны быть отсортированы по возрастанию.
     */
    public Coffee(int[] nominals) {
        this.nominals = nominals;
    }

    /**
     * Считает сдачу.
     * @param value внесённая купюра.
     * @param price цена.
     * @return наименьшее количество монет.
     */
    public int[] changes(int value, int price) {
        int change = value - price;
        int position = this.nominals.length - 1;
        int[] changes = new int[change / this.nominals[0]];
        int count = 0;
        while (change != 0) {
            change -= nominals[position];
            if (change >= 0) {
                changes[count] = nominals[position];
                count++;
            } else {
                change += nominals[position];
                position--;
            }
        }
        return Arrays.copyOf(changes, count);
    }
}
