package ru.job4j.tictactoe;

import java.util.function.BinaryOperator;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    /**
     * Проверяет есть ли в поле выигрышные комбинации для Крестика.
     * @return true - есть выигрышные комбинации, false - нет выигрышных комбинаций.
     */
    public boolean isWinnerX() {
        return isWinner(true);
    }

    /**
     * Проверяет есть ли в поле выигрышные комбинации для Нолика
     * @return true - есть выигрышные комбинации, false - нет выигрышных комбинаций.
     */
    public boolean isWinnerO() {
        return isWinner(false);
    }

    /**
     * Проверяет, есть ли ли пустые клетки для новых ходов.
     * @return true - есть пустые клетки, false - нет пустых клеток.
     */
    public boolean hasGap() {
        boolean result = false;
        int size = this.table.length;
        for (int i = 0; !result && i != size; i++) {
            for (int j = 0; !result && j != size; j++) {
                result = this.table[i][j].hasMarkX() == this.table[i][j].hasMarkO();
            }
        }
        return result;
    }

    /**
     * Проверяет есть ли в поле выигрышные комбинации для
     * значения soughtValue путём проверки выигрышных комбинаций
     * в строках, колонках и диагоналях.
     * @param soughtValue true - для Крестика, false - для Нолика.
     * @return true - есть выигрышные комбинации, false - нет выигрышных комбинаций.
     */
    private boolean isWinner(boolean soughtValue) {
        return this.checkRows(soughtValue)
                || this.checkColumns(soughtValue)
                || this.checkLeftDiagonal(soughtValue)
                || this.checkRightDiagonal(soughtValue);
    }

    /**
     * Ищет выигрышную комбинацию в строках для параметра soughtValue.
     * @param soughtValue true - для Крестика, false - для Нолика.
     * @return true - есть выигрышные комбинации, false - нет выигрышных комбинаций.
     */
    private boolean checkRows(boolean soughtValue) {
        return checkRowColumns(soughtValue,
                (row, column) -> row,
                (row, column) -> column,
                (row, column) -> row,
                (row, column) -> column + 1);
    }

    /**
     * Ищет выигрышную комбинацию в столбцах для параметра soughtValue.
     * @param soughtValue true - для Крестика, false - для Нолика.
     * @return true - есть выигрышные комбинации, false - нет выигрышных комбинаций.
     */
    private boolean checkColumns(boolean soughtValue) {
        return checkRowColumns(soughtValue,
                (row, column) -> column,
                (row, column) -> row,
                (row, column) -> column + 1,
                (row, column) -> row);
    }

    /**
     * Ищет выигрышную комбинацию для параметра soughtValue в диагонали,
     * спускающейся слева направо.
     * @param soughtValue искомое значение: true - для Крестика, false - для Нолика.
     * @return true - в диагонали есть выигрышная комбинация,
     * false - выигрышных комбинаций в диагонали нет.
     */
    private boolean checkLeftDiagonal(boolean soughtValue) {
        return checkDiagonal(soughtValue,
                (i, j) -> i,
                (i, j) -> i,
                (i, j) -> i + 1,
                (i, j) -> i + 1);
    }

    /**
     * Ищет выигрышную комбинацию для параметра soughtValue в диагонали,
     * спускающейся справа налево.
     * @param soughtValue искомое значение: true - для Крестика, false - для Нолика.
     * @return true - в диагонали есть выигрышная комбинация,
     * false - выигрышных комбинаций в диагонали нет.
     */
    private boolean checkRightDiagonal(boolean soughtValue) {
        return checkDiagonal(soughtValue,
                (i, j) -> i,
                (i, j) -> j,
                (i, j) -> i + 1,
                (i, j) -> j - 1);
    }

    /**
     * Ищет выигрышную комбинацию в строках или столбцах для параметра soughtValue.
     * @param soughtValue искомое значение: true - для Крестика, false - для Нолика.
     * @return true - есть выигрышные комбинации, false - нет выигрышных комбинаций.
     */
    private boolean checkRowColumns(boolean soughtValue,
                                    BinaryOperator<Integer> opOne,
                                    BinaryOperator<Integer> opTwo,
                                    BinaryOperator<Integer> opThree,
                                    BinaryOperator<Integer> opFour) {
        boolean result = false;
        int size = this.table.length;
        for (int row = 0; row != size && !result; row++) {
            result = true;
            for (int column = 0; column != size - 1 && result; column++) {
                result = checkResult(row, column, soughtValue, opOne, opTwo, opThree, opFour);
            }
        }
        return result;
    }

    /**
     * Ищет выигрышную комбинацию в диагонали для параметра soughtValue.
     * @param soughtValue искомое значение: true - для Крестика, false - для Нолика.
     * @return true - есть выигрышные комбинации, false - нет выигрышных комбинаций.
     */
    private boolean checkDiagonal(boolean soughtValue,
                                  BinaryOperator<Integer> opOne,
                                  BinaryOperator<Integer> opTwo,
                                  BinaryOperator<Integer> opThree,
                                  BinaryOperator<Integer> opFour) {
        boolean result = true;
        int size = this.table.length - 1;
        for (int i = 0, j = size; i != size && result; i++, j--) {
            result = checkResult(i, j, soughtValue, opOne, opTwo, opThree, opFour);
        }
        return result;
    }

    /**
     * Для текущего и следующего элемента проверяет, что они
     * не пустые, равны друг дугу и искомому значению soughtValue.
     * @param i текущий элемент.
     * @param j следующий элемент.
     * @param soughtValue искомое значение.
     * @return true or false.
     */
    private boolean checkResult(int i, int j, boolean soughtValue,
                                 BinaryOperator<Integer> opOne,
                                 BinaryOperator<Integer> opTwo,
                                 BinaryOperator<Integer> opThree,
                                 BinaryOperator<Integer> opFour) {
        boolean currentGap = this.table[opOne.apply(i, j)][opTwo.apply(i, j)].hasMarkX()
                == this.table[opOne.apply(i, j)][opTwo.apply(i, j)].hasMarkO();
        boolean currentX = this.table[opOne.apply(i, j)][opTwo.apply(i, j)].hasMarkX();
        boolean nextGap = this.table[opThree.apply(i, j)][opFour.apply(i, j)].hasMarkX()
                == this.table[opThree.apply(i, j)][opFour.apply(i, j)].hasMarkO();
        boolean nextX = this.table[opThree.apply(i, j)][opFour.apply(i, j)].hasMarkX();
        return !currentGap && !nextGap
                && currentX == soughtValue && currentX == nextX;
    }
}