package ru.job4j.list;

import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() % rows == 0 ? list.size() / rows : list.size() / rows + 1;
        int[][] array = new int[rows][cells];
        int count = 0;
        for (Integer item : list) {
            array[count / rows][count % rows] = item;
            count++;
        }
        return array;
    }
}