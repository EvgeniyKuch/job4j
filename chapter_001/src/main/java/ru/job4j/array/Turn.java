package ru.job4j.array;

public class Turn {
    public int[] turn(int[] array) {
        for (int index = 0; index < array.length / 2; index++) {
            int buffer = array[index];
            array[index] = array[array.length - 1 - index];
            array[array.length - 1 - index] = buffer;
        }
        return array;
    }
}
