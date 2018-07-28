package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class StoreTest {

    private UserStore userStore;

    @Before
    public void setUp() {
        this.userStore = new UserStore(2);
        this.userStore.add(new User("user1"));
        this.userStore.add(new User("user2"));
    }

    @Test
    public void whenAddThenFindById() {
        assertThat(userStore.findById("user1"), is(new User("user1")));
        assertThat(userStore.findById("user2"), is(new User("user2")));
    }

    @Test
    public void whenDeleteThenNotFound() {
        userStore.delete("user2");
        User result = userStore.findById("user2");
        assertThat(result, is(nullValue()));
    }

    @Test
    public void whenReplacedThenNewUser() {
        userStore.replace("user2", new User("userReplace"));
        User result = userStore.findById("userReplace");
        User expect = new User("userReplace");
        assertThat(result, is(expect));
    }
}