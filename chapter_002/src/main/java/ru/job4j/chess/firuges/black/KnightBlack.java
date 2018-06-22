package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImposibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * Чёрный конь.
 * @author <a href="mailto:evgeniy.kuchumov@gmail.com.com">Кучумов Евгений</a>
 * @version $Id$
 * @since 0.1
 */
public class KnightBlack implements Figure {
    private final Cell position;

    public KnightBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImposibleMoveException {
        Cell[] steps = null;
        int wayY = Math.abs(dest.y - source.y);
        int wayX = Math.abs(dest.x - source.x);
        if (wayX == 1 && wayY == 2 || wayX == 2 && wayY == 1) {
            steps = new Cell[]{dest};
        } else {
            throw new ImposibleMoveException();
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new KnightBlack(dest);
    }
}
