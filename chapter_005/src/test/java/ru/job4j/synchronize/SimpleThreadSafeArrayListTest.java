package ru.job4j.synchronize;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleThreadSafeArrayListTest {

    class Adding implements Runnable {

        private final SimpleThreadSafeArrayList<Integer> array;

        public Adding(SimpleThreadSafeArrayList<Integer> array) {
            this.array = array;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                array.add(1);
            }
        }
    }

    @Test
    public void whenAddTwoMillionsThenSumTwoMillions() throws InterruptedException {
        SimpleThreadSafeArrayList<Integer> array = new SimpleThreadSafeArrayList<>();
        Thread first = new Thread(new Adding(array));
        Thread second = new Thread(new Adding(array));
        first.start();
        second.start();
        first.join();
        second.join();
        int sum = 0;
        for (int i : array) {
            sum += i;
        }
        //если убрать synchronized у метода add(), тест падает
        assertThat(sum, is(2000000));
    }

    @Test
    public void whenAddThreeElementThenGetThreeElement() {
        SimpleThreadSafeArrayList<Integer> array = new SimpleThreadSafeArrayList<>();
        array.add(1);
        array.add(2);
        array.add(3);
        assertThat(array.get(0), is(1));
        assertThat(array.get(1), is(2));
        assertThat(array.get(2), is(3));
    }
}