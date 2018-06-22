package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * Логика проверки хода фигуры.
 * @author <a href="mailto:evgeniy.kuchumov@gmail.com.com">Кучумов Евгений</a>
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    /**
     * Проверяет, что ячейка не пустая, что фигурой в ней
     * можно так ходить, что на пути нет других фигур, и,
     * если можно, копирует фигуру в ячейку назначения.
     * @param source исходная ячейка.
     * @param dest ячейка назначения.
     * @return false - ячейка пустая, или так нельзя ходить,
     * или путь не свободен.
     */
    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        try {
            int index = this.findBy(source);
            Cell[] steps = this.figures[index].way(source, dest);
            this.freeWay(steps);
            rst = true;
            this.figures[index] = this.figures[index].copy(dest);
        } catch (FigureNotFoundException
                | ImposibleMoveException
                | OccupiedWayException e) {
            rst = false;
        }
        return rst;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    /**
     * Проверяет, что ячейка не пустая.
     * @param cell ячейка.
     * @return индекс фигуры в массиве фигур,
     * если ячека не пустая.
     * @throws FigureNotFoundException если ячека пустая.
     */
    private int findBy(Cell cell) throws FigureNotFoundException {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        if (rst == -1) {
            throw new FigureNotFoundException();
        }
        return rst;
    }

    /**
     * Проверяет, что на пути нет других фигур.
     * @param steps массив ячеек, которые надо пройти фигуре.
     * @throws OccupiedWayException путь не свободен.
     */
    private void freeWay(Cell[] steps) throws OccupiedWayException {
        for (int i = 0; i != steps.length; i++) {
            try {
                findBy(steps[i]);
                throw new OccupiedWayException();
            } catch (FigureNotFoundException e) {
                continue;
            }
        }
    }
}
