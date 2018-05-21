package ru.job4j.array;

/**
 * Сортировка пузырьком.
 */
public class BubbleSort {

    /**
     * Сортировка пузырьком.
     * @param array - входной массив.
     * @return - отсортированный по возрастанию массив.
     */
    public int[] sort(int[] array) {
        for (int count = array.length - 1; count > 0; count--) {
            for (int index = 0; index < count; index++) {
                if (array[index] > array[index + 1]) {
                    int buffer = array[index];
                    array[index] = array[index + 1];
                    array[index + 1] = buffer;
                }
            }
        }
        return array;
    }
}
