package ru.job4j.synchronize;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class UserStorageTest {

    private final UserStorage store = new UserStorage();

    class Transfer implements Runnable {
        private final UserStorage store;

        public Transfer(UserStorage store) {
            this.store = store;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                store.transfer(1, 2, 1);
            }
        }
    }

    @Before
    public void setUp() {
        store.add(new User(1, 100));
        store.add(new User(2, 200));
    }

    @Test
    public void whenTransferThenAmountsChange() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(new Transfer(store)).start();
        }

        Thread.sleep(500);

        assertThat(store.getUser(1).getAmount(), is(50));
        assertThat(store.getUser(2).getAmount(), is(250));
    }

    @Test
    public void whenDeleteOneUserThenRemainOneUser() {
        assertThat(store.delete(new User(1, 100)), is(true));
        assertThat(store.delete(new User(1, 100)), is(false));
        assertThat(store.getUser(1), is(nullValue()));
        assertThat(store.getUser(2), is(new User(2, 200)));
    }

    @Test
    public void whenAddReturnTrueAndFalse() {
        assertThat(store.add(new User(1, 50)), is(false));
        assertThat(store.add(new User(3, 50)), is(true));
    }

    @Test
    public void whenUpdateReturnTrueAndFalse() {
        assertThat(store.update(new User(1, 50)), is(true));
        assertThat(store.update(new User(3, 50)), is(false));
    }

    @Test
    public void whenTransferReturnTrueAnFalse() {
        assertThat(store.transfer(3, 2, 100), is(false));
        assertThat(store.transfer(2, 3, 100), is(false));
        assertThat(store.transfer(1, 2, 1000), is(false));
        assertThat(store.transfer(1, 2, 50), is(true));
    }
}