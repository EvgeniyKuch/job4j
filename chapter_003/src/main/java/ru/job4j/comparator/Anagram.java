package ru.job4j.comparator;

/**
 * Анаграмма.
 */
public class Anagram {

    /**
     * Проверяет, являются ли строки анаграммами.
     * Проверяет на равенство сумму кодов символов.
     * @param strOne первая строка.
     * @param strTwo вторая строка.
     * @return true - анаграммы, false - не анаграммы, или длины не равны.
     */
    public boolean check(String strOne, String strTwo) {
        int str1Sum = strOne.codePoints().sum();
        int str2Sum = strTwo.codePoints().sum();
        return str1Sum == str2Sum;
    }
}
