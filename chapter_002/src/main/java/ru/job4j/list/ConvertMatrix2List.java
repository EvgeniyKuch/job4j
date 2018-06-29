package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int[] a : array) {
            for (int b : a) {
                list.add(b);
            }
        }
        return list;
    }
}