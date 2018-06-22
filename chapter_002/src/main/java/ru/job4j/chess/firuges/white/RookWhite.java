package ru.job4j.chess.firuges.white;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.RookBlack;

/**
 * Белая ладья.
 * @author <a href="mailto:evgeniy.kuchumov@gmail.com.com">Кучумов Евгений</a>
 * @version $Id$
 * @since 0.1
 */
public class RookWhite extends RookBlack implements Figure {

    public RookWhite(final Cell position) {
        super(position);
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookWhite(dest);
    }
}
