package ru.job4j.array;

/**
 * Массив заполнен true или false.
 */
public class Check {

    /**
     * Проверяет, что все элементы массива равны true или false.
     * Перебором в цикле сранивает текущий и следующий элемент.
     * При нахождении неравенства выходит из цикла.
     * @param data входной массив элементов типа boolean.
     * @return true - все элементы по диагонали равны true или false,
     * false - не все элементы по диагонали равны true или false.
     */
    public boolean mono(boolean[] data) {
        boolean result = true;
        for (int index = 0; result && index != data.length - 1; index++) {
            result = data[index] == data[index + 1];
        }
        return result;
    }
}
