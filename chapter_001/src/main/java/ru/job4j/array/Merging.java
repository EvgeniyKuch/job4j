package ru.job4j.array;

/**
 * Слияние массивов.
 */
public class Merging {

    /**
     * Слияние двух отсортированных по возрастанию массивов в один отсортированный по возрастанию массив.
     * @param first первый массив типа int[]. Должен быть отсортирован по возрасатнию.
     * @param second второй массив типа int[]. Должен быть отсортирован по возрасатнию.
     * @return - отсортированный по возрастанию массив, состоящий из элементов массивов int[] first и int[] second.
     */
    public int[] arraysMerging(int[] first, int[] second) {
        int[] result = new int[first.length + second.length];
        for (int i = 0, j = 0, k = 0; k != result.length; k++) {
            if (i != first.length && j != second.length) {
                result[k] = first[i] < second[j] ? first[i++] : second[j++];
            } else if (i == first.length) {
                result[k] = second[j++];
            } else {
                result[k] = first[i++];
            }
        }
        return result;
    }
}
