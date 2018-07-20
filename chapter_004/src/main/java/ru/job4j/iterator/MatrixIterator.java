package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {

    private int[][] array;
    private int length;
    private int position = 0;

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
        int[] index = indices(position);
        position++;
        return this.array[index[0]][index[1]];
    }

    private int size(int[][] array) {
        int result = 0;
        for (int[] a : array) {
            result += a.length;
        }
        return result;
    }

    /**
     * Индексы элемента на position.
     * @param position позиция элемента.
     * @return [0] - номер строки, [1] - номер столбца.
     */
    private int[] indices(int position) {
        int i = 0;
        while (position >= 0) {
            position -= this.array[i].length;
            i++;
        }
        i--;
        int j = position + array[i].length;
        return new int[]{i, j};
    }
}
