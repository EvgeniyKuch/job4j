package ru.job4j.array;

/**
 * Перевернуть массив.
 */
public class Turn {

    /**
     * Метод переворачивает массив задом наперёд.
     * @param array входной массив.
     * @return перевернутый массив.
     */
    public int[] turn(int[] array) {
        for (int index = 0; index < array.length / 2; index++) {
            int buffer = array[index];
            array[index] = array[array.length - 1 - index];
            array[array.length - 1 - index] = buffer;
        }
        return array;
    }
}
