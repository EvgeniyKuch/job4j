package ru.job4j.array;

import java.util.Arrays;

/**
 * Удаление дубликатов в массиве.
 */
public class ArrayDuplicate {

    /**
     * Удаляет дубликаты в массиве строк.
     * @param array - исходный массив.
     * @return - массив без дубликатов.
     */
    public String[] remove(String[] array) {
        int end = array.length - 1;
        for (int i = 0; i <= end; i++) {
            for (int j = i + 1; j <= end; j++) {
                if (array[i].equals(array[j])) {
                    String buffer = array[j];
                    array[j] = array[end];
                    array[end] = buffer;
                    end--;
                }
            }
        }
        return Arrays.copyOf(array, end + 1);
    }
}
