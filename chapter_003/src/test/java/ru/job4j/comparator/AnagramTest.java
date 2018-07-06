package ru.job4j.comparator;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AnagramTest {

    @Test
    public void whenAnagramThenTrue() {
        String strOne = "rail safety";
        String strTwo = "fairy tales";
        Anagram anagram = new Anagram();
        assertThat(anagram.check(strOne, strTwo), is(true));
    }

    @Test
    public void whenNotAnagramThenFalse() {
        String strOne = "fail safety";
        String strTwo = "fairy tales";
        Anagram anagram = new Anagram();
        assertThat(anagram.check(strOne, strTwo), is(false));
    }

    @Test
    public void whenDiffLengthsThenFalse() {
        String strOne = "rail safety";
        String strTwo = "fairy tales asdf";
        Anagram anagram = new Anagram();
        assertThat(anagram.check(strOne, strTwo), is(false));
    }
}
