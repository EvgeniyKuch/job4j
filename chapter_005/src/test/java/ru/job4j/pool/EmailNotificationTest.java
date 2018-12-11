package ru.job4j.pool;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class EmailNotificationTest {

    @Test
    public void whenSend2EmailsThenCountEquals2() {
        EmailNotification emn = new EmailNotification();
        emn.emailTo(new User("Evgen", "geka.1985@mail.ru"));
        emn.emailTo(new User("Vladimir", "vladimir@mail.ru"));
        emn.close();
        assertThat(emn.sentCounter(), is(2));
    }
}