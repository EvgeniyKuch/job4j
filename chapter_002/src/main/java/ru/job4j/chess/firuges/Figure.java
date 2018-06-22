package ru.job4j.chess.firuges;

import ru.job4j.chess.ImposibleMoveException;

import java.util.function.BiPredicate;

/**
 * Интерфейс фигуры.
 * @author <a href="mailto:evgeniy.kuchumov@gmail.com.com">Кучумов Евгений</a>
 * @version $Id$
 * @since 0.1
 */
public interface Figure {

    /**
     * Позиция фигуры на доске.
     * @return позиция.
     */
    Cell position();

    /**
     * Проверяет, можно ли так ходить фигурой.
     * Если можно, возвращает массив ячеек,
     * представляющий собой путь фигуры.
     * @param source исходная позиция.
     * @param dest конечная позиция
     * @return массив ячеек.
     * @throws ImposibleMoveException выбрасываемое исключение,
     * если так ходить нельзя.
     */
    Cell[] way(Cell source, Cell dest) throws ImposibleMoveException;

    /**
     * Копирование фигуры в заданную ячейку.
     * @param dest ячейка назначения.
     * @return - данная скопированная фигура.
     */
    Figure copy(Cell dest);

    /**
     * Путь к иконке с фигурой.
     * @return путь к иконке с фигурой.
     */
    default String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );
    }

    /**
     * Возвращает ячейку с заданными координатами.
     * @param x координата x.
     * @param y координата Y.
     * @return ячейка с координатами (x,y).
     */
    default Cell posCell(int x, int y) {
        Cell rst = null;
        for (int i = 0; i != Cell.values().length; i++) {
            if (Cell.values()[i].x == x && Cell.values()[i].y == y) {
                rst = Cell.values()[i];
                break;
            }
        }
        return rst;
    }

    /**
     * Проверяет, можно ли так ходить фигурой.
     * Если можно, возвращает массив ячеек,
     * представляющий собой путь фигуры.
     * @param predicate условие, при выполнении которого
     *                  можно так ходить.
     * @param source исходная позиция.
     * @param dest конечная позиция
     * @return массив ячеек.
     * @throws ImposibleMoveException выбрасываемое исключение,
     * если так ходить нельзя.
     */
    default Cell[] wayPossible(BiPredicate<Cell, Cell> predicate,
                              Cell source, Cell dest)
            throws ImposibleMoveException {
        Cell[] steps = null;
        if (predicate.test(source, dest)) {
            int wayX = Math.abs(dest.x - source.x);
            int wayY = Math.abs(dest.y - source.y);
            int deltaX = wayX == 0 ? 0 : (dest.x - source.x) / wayX;
            int deltaY = wayY == 0 ? 0 : (dest.y - source.y) / wayY;
            int length = wayX > wayY ? wayX : wayY;
            steps = new Cell[length];
            for (int i = 0, x = source.x, y = source.y; i != length; i++) {
                x += deltaX;
                y += deltaY;
                steps[i] = posCell(x, y);
            }
        } else {
            throw new ImposibleMoveException();
        }
        return steps;
    }
}
