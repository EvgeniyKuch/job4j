package ru.job4j.array;

/**
 * Квадратный массив заполнен true или false по диагонали.
 */
public class MatrixCheck {

    /**
     * Проверяет, что все элементы по диагонали равны true или false.
     * Перебором в цикле сранивает текущий и следующий элемент в диагонали..
     * При нахождении неравенства выходит из цикла.
     * @param data входная матрица элементов типа boolean.
     * @return true - все элементы по диагонали равны true или false,
     * false - не все элементы по диагонали равны true или false.
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        for (int index = 0; result && index != data[0].length - 1; index++) {
            result = data[index][index] == data[index + 1][index + 1];
        }
        return result;
    }
}
