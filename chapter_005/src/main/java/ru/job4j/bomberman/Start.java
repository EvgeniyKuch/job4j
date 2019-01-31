package ru.job4j.bomberman;

import java.util.concurrent.ThreadLocalRandom;

public class Start {
    public static void main(String[] args) throws InterruptedException {
        long timeStart = System.currentTimeMillis();
        boolean gameOver = false;
        Board board = new Board(5, 5);
        board.setBlock(2, 2);
        Thread monsterOne = new Thread(new Monster(board, 4, 4));
        Thread monsterTwo = new Thread(new Monster(board, 4, 0));
        monsterOne.setDaemon(true);
        monsterTwo.setDaemon(true);
        monsterOne.start();
        monsterTwo.start();
        Bomberman hero = new Bomberman(board, 0, 0);
        while (!gameOver) {
            Thread.sleep(50);
            int rnd = ThreadLocalRandom.current().nextInt(4);
            hero.move(rnd);
            gameOver = hero.hasQueuedThreads();
        }
        System.out.println("Game Over. Game duration: "
                + (System.currentTimeMillis() - timeStart)
                + " milliseconds."
        );
    }
}
