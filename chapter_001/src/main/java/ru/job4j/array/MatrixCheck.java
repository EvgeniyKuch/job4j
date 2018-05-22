package ru.job4j.array;

/**
 * Квадратный массив заполнен true или false по диагонали.
 */
public class MatrixCheck {

    /**
     * Проверяет, что все элементы по диагонали равны true или false.
     * @param data - входная матрица элементов типа boolean.
     * @return - true - все элементы по диагонали равны true или false,
     * false - не все элементы по диагонали равны true или false.
     */
    public boolean mono(boolean[][] data) {
        boolean result = false;
        for (int index = 0; index < data[0].length - 1; index++) {
            result = data[index][index] == data[index + 1][index + 1];
            if (!result) {
                break;
            }
        }
        return result;
    }
}
