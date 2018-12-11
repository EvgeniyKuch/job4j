package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class EmailNotification {

    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
        );
    private AtomicInteger sentEmailsCounter = new AtomicInteger(0); // для тестирования

    public EmailNotification() {

    }

    public int sentCounter() {
        return sentEmailsCounter.get();
    }

    public void emailTo(User user) {
        String subject = "Notification " + user.getUsername()
                + " to email " + user.getEmail();
        String body = "Add a new event to " + user.getUsername();
        pool.submit(() -> send(subject, body, user.getEmail()));
    }

    private void send(String subject, String body, String email) {
        sentEmailsCounter.incrementAndGet(); //для тестирования
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
