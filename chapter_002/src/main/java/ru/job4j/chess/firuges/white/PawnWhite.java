package ru.job4j.chess.firuges.white;

import ru.job4j.chess.ImposibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.PawnBlack;

/**
 * Белая пешка.
 * @author <a href="mailto:evgeniy.kuchumov@gmail.com.com">Кучумов Евгений</a>
 * @version $Id$
 * @since 0.1
 */
public class PawnWhite extends PawnBlack implements Figure {

    public PawnWhite(final Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImposibleMoveException {
        return wayPossible(
                (s, d) -> (d.y == s.y + 1 && s.x == d.x),
                source, dest);
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnWhite(dest);
    }
}
