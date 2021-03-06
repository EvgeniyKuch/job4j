package ru.job4j.loop;

/**
 * Board - шахматная доска.
 */
public class Board {

    /**
     * Метод paint рисует шахматную доску из символов 'X' и пробелов.
     * @param width ширина доски.
     * @param height высота доски.
     * @return шахматная доска из символов 'X' и пробелов.
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i + j) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }
}
