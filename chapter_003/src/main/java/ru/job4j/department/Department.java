package ru.job4j.department;

import java.util.*;
import java.util.stream.Collectors;

public class Department {
    /**
     * Сортировка по возрастанию
     * в естественном порядке.
     * @param depts справочник департаментов.
     * @return отсортрованный по возрастанию список
     * департаментов.
     */
    public List<String> sortAsc(List<String> depts) {
        return new ArrayList<>(this.hierarchy(depts))
                .stream().sorted().collect(Collectors.toList());
    }

    /**
     * Сортировка по убыванию с пом. компаратора.
     * @param depts справочник департаментов.
     * @return отсортрованный по убыванию список
     * департаментов.
     */

    public List<String> sortDesc(List<String> depts) {
        return new ArrayList<>(this.hierarchy(depts))
                .stream().sorted(this::compareStringDesc)
                .collect(Collectors.toList());
    }

    /**
     * Cравнение строк для сортировки
     * по убыванию.
     * @param strOne первая строка.
     * @param strTwo вторая строка.
     * @return Целое отрицательное или положительное,
     * если первое больше второго или меньше
     * соответственно. Ноль не возвращает, т.к.
     * дубликатов по логике быть не должно.
     */
    private int compareStringDesc(String strOne, String strTwo) {
        String[] strOneArr = strOne.split("\\\\");
        String[] strTwoArr = strTwo.split("\\\\");
        int length = strOneArr.length < strTwoArr.length
                ? strOneArr.length : strTwoArr.length;
        int result = strOneArr.length < strTwoArr.length
                ? -1 : 1;
        for (int i = 0; i < length; i++) {
            if (!strOneArr[i].equals(strTwoArr[i])) {
                result = strTwoArr[i].compareTo(strOneArr[i]);
                break;
            }
        }
        return result;
    }

    /**
     * Разбивает каждую строку на массив строк с иерархией
     * и добавляет в множество, исключающее дубликаты.
     * @param depts справочник департаментов.
     * @return
     */
    private Set<String> hierarchy(List<String> depts) {
        Set<String> result = new HashSet<>();
        for (String dept : depts) {
            result.addAll(this.hierarchySplit(dept));
        }
        return result;
    }

    /**
     * Разбивает строку на массив строк с иерархией.
     * Например, из строки
     *              "K1\SK1\SSK1"
     * получаем
     *              K1
     *              K1\SK1
     *              K1\SK1\SSK1
     *
     * Таким образом получаем все недостающие
     * строки с кодом верхнеуровнего подразделения.
     * @param dept департамент
     * @return список с иерархией
     */
    private List<String> hierarchySplit(String dept) {
        String[] depts = dept.split("\\\\");
        for (int i = 1; i < depts.length; i++) {
            depts[i] = String.join("\\", depts[i - 1], depts[i]);
        }
        return new ArrayList<>(Arrays.asList(depts));
    }
}
