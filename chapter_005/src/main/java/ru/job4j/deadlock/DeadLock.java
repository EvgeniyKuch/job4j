package ru.job4j.deadlock;

import java.util.concurrent.CountDownLatch;

public class DeadLock {
    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(2);
        A a = new A(cdl);
        B b = new B(cdl);
        a.setB(b);
        b.setA(a);
        new Thread(new ThreadA(a)).start();
        new Thread(new ThreadB(b)).start();
    }
}

class A {
    private B b;
    private CountDownLatch cdl;

    public A(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    public void setB(B b) {
        this.b = b;
    }

    public synchronized void someMethod() {
        try {
            cdl.countDown();
            cdl.await();
            b.someMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class B {
    private A a;
    private CountDownLatch cdl;

    public B(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    public void setA(A a) {
        this.a = a;
    }

    public synchronized void someMethod() {
        try {
            cdl.countDown();
            cdl.await();
            a.someMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadA implements Runnable {

    private A a;

    public ThreadA(A a) {
        this.a = a;
    }

    @Override
    public void run() {
            a.someMethod();
    }
}

class ThreadB implements Runnable {

    private B b;

    public ThreadB(B b) {
        this.b = b;
    }

    @Override
    public void run() {
            b.someMethod();
    }
}
