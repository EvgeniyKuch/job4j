package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length * array[0].length; i++) {
            list.add(array[i / array[0].length][i % array[0].length]);
        }
        return list;
    }
}