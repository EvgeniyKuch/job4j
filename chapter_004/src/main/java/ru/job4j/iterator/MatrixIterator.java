package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {

    private int[][] array;
    private int row = 0;
    private int column = 0;

    public MatrixIterator(int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return row < array.length && column < array[row].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int result = array[row][column];
        column++;
        if (column == array[row].length) {
            column = 0;
            row++;
        }
        return result;
    }
}
