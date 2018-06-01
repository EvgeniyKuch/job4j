package ru.job4j.array;

/**
 * Square - возведение в квадрат.
 */
public class Square {

    /**
     * Заполняет массив элементами от 1 до bound, возведенными в квадрат.
     * @param bound размер массива.
     * @return массив, заполненный элементами от 1 до bound, возведенными в квадрат.
     */
    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        for (int i = 0; i < bound; i++) {
            rst[i] = (i + 1) * (i + 1);
        }
        return rst;
    }
}
