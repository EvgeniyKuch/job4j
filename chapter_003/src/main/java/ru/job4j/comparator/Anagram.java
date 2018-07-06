package ru.job4j.comparator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Анаграмма.
 */
public class Anagram {

    /**
     * Проверяет, являются ли строки анаграммами.
     * Берёт по очереди каждый символ из первой
     * строки и ищет его во второй строке.
     * Если находит - удаляет из обеих строк.
     * Если оба массивы по окончании пусты,
     * то строки - анаграммы.
     * @param strOne первая строка.
     * @param strTwo вторая строка.
     * @return true - анаграммы, false - не анаграммы, или длины не равны.
     */
    public boolean check(String strOne, String strTwo) {
        List<Integer> str1 = strOne.codePoints().boxed().collect(Collectors.toList());
        List<Integer> str2 = strTwo.codePoints().boxed().collect(Collectors.toList());
        str1.removeIf(str2::remove);
        return str1.size() == 0 && str2.size() == 0;
    }
}
