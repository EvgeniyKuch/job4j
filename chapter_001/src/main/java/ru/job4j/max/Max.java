package ru.job4j.max;

/**
 * Максимум из двух и трех чисел.
 *@author Kuchumov (geka.1985@mail.ru)
 *@version $Id$
 *@since 16.05.2018
 */
public class Max {

    /**
     * Максимум из 2-х чисел.
     * @param first - первое число.
     * @param second - второе число.
     * @return - максимум.
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }
}
