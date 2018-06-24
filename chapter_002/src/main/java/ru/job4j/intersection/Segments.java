package ru.job4j.intersection;


public class Segments {
    private int a;
    private int b;
    private int c;
    private int d;

    public Segments(int a, int b, int c, int d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
    }

    public boolean intersection() {
        return c < b && a < d;
    }
}
