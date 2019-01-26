package ru.job4j.bomberman;

/**
 * Класс Бомбермена.
 */
public class Bomberman {
    private final Board board;
    private int x;
    private int y;

    public Bomberman(final Board board, int startX, int startY) {
        this.board = board;
        this.x = startX;
        this.y = startY;
        board.get(x, y).lock();
    }

    /**
     * Метод для управления пользователя Бомберменом.
     * @param dest - направление движения:
     *         Up:    0      0
     *         Right: 1    3 ☺ 1
     *         Down:  2      2
     *         Left:  3
     * @return true - движение совершено, false - движение соверщить нельзя.
     * @throws InterruptedException
     */
    public boolean move(int dest) throws InterruptedException {
        int deltaX = (2 - dest) % 2;
        int deltaY = (dest - 1) % 2;
        boolean possibility = !(
                x + deltaX < 0 || x + deltaX >= board.height()
                        || y + deltaY < 0 || y + deltaY >= board.width()
        );
        boolean result = possibility
                && board.move(x, y, x + deltaX, y + deltaY);
        x += deltaX;
        y += deltaY;
        return result;
    }
}
