package ru.job4j.bomberman;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Класс чудовища, двигается сам.
 */
public class Monster implements Runnable {

    private final Board board;
    private int x;
    private int y;

    public Monster(final Board board, int startX, int startY) {
        this.board = board;
        this.x = startX;
        this.y = startY;
        board.get(x, y).lock();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
                int deltaX;
                int deltaY;
                do {
                    do {
                        int rnd = ThreadLocalRandom.current().nextInt(4);
                        // Up:    0      0
                        // Right: 1    3 ☺ 1
                        // Down:  2      2
                        // Left:  3
                        deltaX = (2 - rnd) % 2;
                        deltaY = (rnd - 1) % 2;
                    } while (x + deltaX < 0
                            || x + deltaX >= board.height()
                            || y + deltaY < 0
                            || y + deltaY >= board.width());
                } while (!board.move(x, y, x + deltaX, y + deltaY));
                x += deltaX;
                y += deltaY;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
