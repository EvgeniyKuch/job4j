package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.*;
import ru.job4j.chess.firuges.white.PawnWhite;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LogicTest {

    /**
     * Тест метода public boolean ru.job4j.chess.Logic.move(Cell source, Cell dest).
     * Есть ли в исходной клетке фигура?
     * Можно ли так ходить данной фигурой?
     * Свободен ли путь?
     */
    @Test
    public void whenBishopBlackPossibleMoveOnBoard() {
        Figure bishopBlack = new BishopBlack(Cell.E5);
        Logic logic = new Logic();
        logic.add(bishopBlack);
        assertThat(logic.move(Cell.E5, Cell.B8), is(true));
    }

    /**
     * Тест метода public boolean ru.job4j.chess.Logic.move(Cell source, Cell dest).
     * Есть ли в исходной клетке фигура?
     * Можно ли так ходить данной фигурой?
     * Свободен ли путь?
     */
    @Test
    public void whenBishopBlackImpossibleMoveOnBoard() {
        Figure bishopBlack = new BishopBlack(Cell.E5);
        Logic logic = new Logic();
        logic.add(bishopBlack);
        assertThat(logic.move(Cell.E5, Cell.B7), is(false));
    }

    /**
     * Тест метода private void ru.job4j.chess.Logic.freeWay(Cell[] steps) throws OccupiedWayException.
     * Свободен ли путь?
     */
    @Test
    public void whenBishopBlackOccupiedWayException() {
        Figure bishopBlack = new BishopBlack(Cell.E5);
        Figure pawnBlack = new PawnBlack(Cell.G7);
        Logic logic = new Logic();
        logic.add(bishopBlack);
        logic.add(pawnBlack);
        assertThat(logic.move(Cell.E5, Cell.H8), is(false));
    }

    /**
     * Тест метода private int ru.job4j.chess.Logic.findBy(Cell cell) throws FigureNotFoundException.
     * Есть ли фигура на исходной позиции?
     */
    @Test
    public void whenFigureNotFoundException() {
        Logic logic = new Logic();
        assertThat(logic.move(Cell.D4, Cell.A1), is(false));
    }

    /**
     * Тест Слона.
     */
    @Test
    public void whenBishopBlackPossibleMove() {
        Figure bishopBlack = new BishopBlack(Cell.E5);
        Cell[] steps = new Cell[]{Cell.F4, Cell.G3, Cell.H2};
        assertThat(bishopBlack.way(Cell.E5, Cell.H2), is(steps));
    }

    /**
     * Тест Короля.
     */
    @Test
    public void whenKingBlackPossibleMove() {
        Figure kingBlack = new KingBlack(Cell.E5);
        Cell[] steps = new Cell[]{Cell.D4};
        assertThat(kingBlack.way(Cell.E5, Cell.D4), is(steps));
    }

    /**
     * Тест Коня.
     */
    @Test
    public void whenKnightBlackPossibleMove() {
        Figure knightBlack = new KnightBlack(Cell.F2);
        Cell[] steps = new Cell[]{Cell.D3};
        assertThat(knightBlack.way(Cell.F2, Cell.D3), is(steps));
    }

    /**
     * Тест Ладьи.
     */
    @Test
    public void whenRookBlackPossibleMove() {
        Figure rookBlack = new RookBlack(Cell.H1);
        Cell[] steps = new Cell[]{Cell.H2, Cell.H3, Cell.H4, Cell.H5, Cell.H6, Cell.H7, Cell.H8};
        assertThat(rookBlack.way(Cell.H1, Cell.H8), is(steps));
    }

    /**
     * Тест Ферзя.
     */
    @Test
    public void whenQeenBlackPossibleMove() {
        Figure qeenBlack = new QeenBlack(Cell.H1);
        Cell[] steps = new Cell[]{Cell.B2, Cell.C3, Cell.D4, Cell.E5, Cell.F6, Cell.G7, Cell.H8};
        assertThat(qeenBlack.way(Cell.A1, Cell.H8), is(steps));
    }

    /**
     * Тест чёрной пешки.
     */
    @Test
    public void whenPawnBlackPossibleMove() {
        Figure pawnBlack = new PawnBlack(Cell.D7);
        Cell[] steps = new Cell[]{Cell.D6};
        assertThat(pawnBlack.way(Cell.D7, Cell.D6), is(steps));
    }

    /**
     * Тест белой пешки.
     */
    @Test
    public void whenPawnWhitePossibleMove() {
        Figure pawnWhite = new PawnWhite(Cell.E2);
        Cell[] steps = new Cell[]{Cell.E3};
        assertThat(pawnWhite.way(Cell.E2, Cell.E3), is(steps));
    }
}
