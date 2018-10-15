package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        double x = this.rect.getX();
        double y = this.rect.getY();
        double deltaX = Math.random() * 20 - 10;
        double deltaY = Math.random() * 20 - 10;
        while (!Thread.interrupted()) {
            x += deltaX;
            y += deltaY;
            if (x > 290.0 || x < 0.0) {
                deltaX = -deltaX;
            }
            if (y > 290.0 || y < 0.0) {
                deltaY = -deltaY;
            }
            this.rect.setX(x);
            this.rect.setY(y);
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}