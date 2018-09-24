package ru.job4j.trie;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class TrieTest {

    @Test
    public void whenPut3ElementsThenGet3Elements() {
        Trie<Integer> trie = new Trie<>();
        trie.put("One", 1);
        trie.put("Two", 2);
        trie.put("Ond", 3);
        trie.put("Ond", 4);

        assertThat(trie.get("One"), is(1));
        assertThat(trie.get("Two"), is(2));
        assertThat(trie.get("Ond"), is(4));
        assertThat(trie.get("Three"), is(nullValue()));
    }

    @Test
    public void whenPut1ElementThenContainIt() {
        Trie<Integer> trie = new Trie<>();
        trie.put("One", 1);

        assertThat(trie.contains("One"), is(true));
        assertThat(trie.contains("Two"), is(false));
    }

    @Test
    public void whenDelete1ElementThenDoesNotContainIt() {
        Trie<Integer> trie = new Trie<>();
        trie.put("One", 1);

        assertThat(trie.delete("One"), is(1));
        assertThat(trie.delete("One"), is(nullValue()));
        assertThat(trie.delete("Two"), is(nullValue()));
        assertThat(trie.contains("One"), is(false));
    }
}