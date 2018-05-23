package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.junit.Assert.assertThat;

public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        //напишите здесь тест, проверяющий удаление дубликатов строк из массива строк.
        ArrayDuplicate duplicate = new ArrayDuplicate();
        String[] inputArray = {"Привет", "Мир", "Привет", "Супер", "Мир", "Супер"};
        String[] resultArray = duplicate.remove(inputArray);
        String[] expectArray = {"Привет", "Мир", "Супер"};
        assertThat(resultArray, arrayContainingInAnyOrder(expectArray));
    }

    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate1() {
        //напишите здесь тест, проверяющий удаление дубликатов строк из массива строк.
        ArrayDuplicate duplicate = new ArrayDuplicate();
        String[] inputArray = {"Привет", "Привет", "Мир", "Привет", "Супер", "Мир", "Супер", "Привет"};
        String[] resultArray = duplicate.remove(inputArray);
        String[] expectArray = {"Привет", "Мир", "Супер"};
        assertThat(resultArray, arrayContainingInAnyOrder(expectArray));
    }
}
