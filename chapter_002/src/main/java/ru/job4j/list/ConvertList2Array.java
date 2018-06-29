package ru.job4j.list;

import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() % rows == 0 ? list.size() / rows : list.size() / rows + 1;
        int[][] array = new int[rows][cells];
        for (int i = 0; i != rows; i++) {
            for (int j = 0; j < cells && (i * cells + j < list.size()); j++){
                    array[i][j] = list.get(i * cells + j);
            }
        }
        return array;
    }
}