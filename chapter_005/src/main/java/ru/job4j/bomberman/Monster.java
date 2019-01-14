package ru.job4j.bomberman;

import java.util.concurrent.ThreadLocalRandom;

public class Monster implements Runnable {

    private final Board board;
    private int x;
    private int y;

    public Monster(Board board, int startX, int startY) {
        this.board = board;
        this.x = startX;
        this.y = startY;
    }

    @Override
    public void run() {
        board.get(x, y).lock();
        while (!Thread.currentThread().isInterrupted()) {
            try {
                print(board.width(), board.height(), x, y);
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

    private void print(int width, int height, int x, int y) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                str.append(i == x && j == y ? '+' : '-');
            }
            str.append(System.lineSeparator());
        }
        System.out.println(str);
    }
}
