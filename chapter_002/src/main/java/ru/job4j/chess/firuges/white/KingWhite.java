package ru.job4j.chess.firuges.white;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.KingBlack;

/**
 * Белый король.
 * @author <a href="mailto:evgeniy.kuchumov@gmail.com.com">Кучумов Евгений</a>
 * @version $Id$
 * @since 0.1
 */
public class KingWhite extends KingBlack implements Figure {

    public KingWhite(final Cell position) {
        super(position);
    }

    @Override
    public Figure copy(Cell dest) {
        return new KingWhite(dest);
    }
}
