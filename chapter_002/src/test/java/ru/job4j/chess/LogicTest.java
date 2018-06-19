package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.black.PawnBlack;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LogicTest {

    @Test
    public void whenBishopBlackPossibleMove() {
        Figure bishopBlack = new BishopBlack(Cell.E5);
        Cell[] steps = new Cell[]{Cell.F4, Cell.G3, Cell.H2};
        assertThat(bishopBlack.way(Cell.E5, Cell.H2), is(steps));
    }

    @Test
    public void whenBishopBlackPossibleMoveOnBoard() {
        Figure bishopBlack = new BishopBlack(Cell.E5);
        Logic logic = new Logic();
        logic.add(bishopBlack);
        assertThat(logic.move(Cell.E5, Cell.B8), is(true));
    }

    @Test
    public void whenBishopBlackImpossibleMoveOnBoard() {
        Figure bishopBlack = new BishopBlack(Cell.E5);
        Logic logic = new Logic();
        logic.add(bishopBlack);
        assertThat(logic.move(Cell.E5, Cell.B7), is(false));
    }

    @Test
    public void whenBishopBlackOccupiedWayException() {
        Figure bishopBlack = new BishopBlack(Cell.E5);
        Figure pawnBlack = new PawnBlack(Cell.G7);
        Logic logic = new Logic();
        logic.add(bishopBlack);
        logic.add(pawnBlack);
        assertThat(logic.move(Cell.E5, Cell.H8), is(false));
    }

    @Test
    public void whenFigureNotFoundException() {
        Logic logic = new Logic();
        assertThat(logic.move(Cell.D4, Cell.A1), is(false));
    }
}
