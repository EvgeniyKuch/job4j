package ru.job4j.condition;

/**
 * Класс Point описывает точку в системе координат.
 */
public class Point {
    private int x;
    private int y;

    /**
     * Конструктор
     * @param x координата X.
     * @param y координата Y.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Расстояние между текущей точкой и точкой that.
     * @param that вторая точка.
     * @return расстояние между текущей точкой и точкой that.
     */
    public double distanceTo(Point that) {
        return Math.sqrt(
                Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2)
        );
    }

    public static void main(String[] args) {
        Point a = new Point(0, 1);
        Point b = new Point(2, 5);

        System.out.println("x1 = " + a.x);
        System.out.println("y1 = " + a.y);
        System.out.println("x2 = " + b.x);
        System.out.println("y2 = " + b.y);

        double result = a.distanceTo(b);
        System.out.println("Расстояние между точками А и В : " + result);
    }
}
