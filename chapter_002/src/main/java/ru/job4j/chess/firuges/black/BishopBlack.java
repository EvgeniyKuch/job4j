package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImposibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImposibleMoveException {
        Cell[] steps = null;
        if (Math.abs(dest.y - source.y) == Math.abs(dest.x - source.x)) {
            steps = new Cell[Math.abs(dest.y - source.y)];
            for (int i = 0, x = source.x, y = source.y; x != dest.x; i++) {
                x = dest.x > x ? ++x : --x;
                y = dest.y > y ? ++y : --y;
                steps[i] = this.posCell(x, y);
            }
        } else {
            throw new ImposibleMoveException();
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
