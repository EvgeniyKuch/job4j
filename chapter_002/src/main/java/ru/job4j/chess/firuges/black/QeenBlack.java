package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * Чёрный ферзь.
 * @author <a href="mailto:evgeniy.kuchumov@gmail.com.com">Кучумов Евгений</a>
 * @version $Id$
 * @since 0.1
 */
public class QeenBlack implements Figure {
    private final Cell position;

    public QeenBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        return wayPossible(
                (s, d) -> (
                        Math.abs(d.y - s.y) == Math.abs(d.x - s.x)
                                || (d.y - s.y) == 0
                                || (d.x - s.x) == 0
                ),
                source, dest);
    }

    @Override
    public Figure copy(Cell dest) {
        return new QeenBlack(dest);
    }
}
