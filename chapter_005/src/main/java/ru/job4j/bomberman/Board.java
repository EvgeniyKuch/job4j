package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Board {

    private final ReentrantLock[][] board;

    public Board(int width, int height) {
        this.board = new ReentrantLock[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
    }

    public ReentrantLock get(int x, int y) {
        return board[x][y];
    }

    public int width() {
        return board[0].length;
    }

    public int height() {
        return board.length;
    }

    public void setBlock(int x, int y) {
        if (x >= 0 && x < board[0].length
                && y >= 0 && y < board.length) {
            board[x][y].lock();
        }
    }

    public boolean move(int sourceX, int sourceY, int destX, int destY)
            throws InterruptedException {
        boolean result = false;
        if (board[destX][destY].tryLock(500, TimeUnit.MILLISECONDS)) {
            board[sourceX][sourceY].unlock();
            result = true;
        }
        return result;
    }

    public static void main(String[] args) {
        Board board = new Board(3, 3);
        board.setBlock(1, 1);
        new Thread(new Monster(board, 0, 0)).start();
    }
}
