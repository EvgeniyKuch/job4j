package ru.job4j.comparator;

/**
 * Анаграмма.
 */
public class Anagram {

    /**
     * Проверяет, являются ли строки анаграммами.
     * Проверяет на равенство сумму квадратов кодов символов.
     * @param strOne первая строка.
     * @param strTwo вторая строка.
     * @return true - анаграммы, false - не анаграммы, или длины не равны.
     */
    public boolean check(String strOne, String strTwo) {
        int strOneSumSquares = strOne.codePoints().map(i -> i * i).sum();
        int strTwoSumSquares = strTwo.codePoints().map(i -> i * i).sum();
        return strOneSumSquares == strTwoSumSquares;
    }
}
