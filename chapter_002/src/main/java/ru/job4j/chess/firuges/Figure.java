package ru.job4j.chess.firuges;

import ru.job4j.chess.ImposibleMoveException;

public interface Figure {
    Cell position();

    Cell[] way(Cell source, Cell dest) throws ImposibleMoveException;

    Figure copy(Cell dest);

    default String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );

    }

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
}
