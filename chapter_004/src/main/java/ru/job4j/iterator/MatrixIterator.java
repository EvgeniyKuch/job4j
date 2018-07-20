package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {

    private int[][] array;
    private int length;
    private int position = 0;
    private int row = 0;
    private int column = 0;

    public MatrixIterator(int[][] array) {
        this.array = array;
        this.length = this.size(array);
    }

    @Override
    public boolean hasNext() {
        return position < length;
    }

    @Override
    public Integer next() {
        if (position >= length) {
            throw new NoSuchElementException();
        }
        int result = this.array[row][column];
        position++;
        column++;
        if (column == this.array[row].length) {
            column = 0;
            row++;
        }
        return result;
    }

    private int size(int[][] array) {
        int result = 0;
        for (int[] a : array) {
            result += a.length;
        }
        return result;
    }
}
