package ru.job4j.array;

/**
 * Таблица умножения
 */
public class Matrix {

    /**
     * Таблица умножения.
     * @param size - размер таблицы.
     * @return - матрица size x size.
     */
    int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }
}
