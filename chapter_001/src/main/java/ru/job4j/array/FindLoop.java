package ru.job4j.array;

/**
 * Классический поиск перебором.
 */
public class FindLoop {

    /**
     * Классический поиск перебором.
     * @param data входной массив.
     * @param el искомый элемент.
     * @return индекс найденного элемента. Если элемент не найден, возвращает -1.
     */
    public int indexOf(int[] data, int el) {
        int rst = -1;
        for (int index = 0; rst == -1 && index != data.length; index++) {
            if (data[index] == el) {
                rst = index;
            }
        }
        return rst;
    }
}
