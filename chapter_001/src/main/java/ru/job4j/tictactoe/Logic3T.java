package ru.job4j.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    /**
     * Проверяет есть ли в поле выигрышные комбинации для Крестика
     * путём проверки выигрышных комбинаций в строках, колонках и диагоналях.
     * @return true - есть выигрышные комбинации, false - нет выигрышных комбинаций.
     */
    public boolean isWinnerX() {
        return this.checkRows(true)
                || this.checkColumns(true)
                || this.checkLeftDiagonal(true)
                || this.checkRightDiagonal(true);

    }

    /**
     * Проверяет есть ли в поле выигрышные комбинации для Нолика
     * путём проверки выигрышных комбинаций в строках, колонках и диагоналях.
     * @return true - есть выигрышные комбинации, false - нет выигрышных комбинаций.
     */
    public boolean isWinnerO() {
        return this.checkRows(false)
                || this.checkColumns(false)
                || this.checkLeftDiagonal(false)
                || this.checkRightDiagonal(false);
    }

    /**
     * Проверяет, есть ли ли пустые клетки для новых ходов
     * путём последовательной проверки каждой клетки на пустоту.
     * При нахождении первой пустой клетки выходит из цикла.
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
     * Ищет выигрышную комбинацию в строках для параметра soughtValue.
     * Проверяет в цикле строки. В каждой строке для текущего и следующего
     * элемента проверяет, что они не пустые, равны друг дугу и искомому
     * значению soughtValue. Если условия не выполняются на какой-либо итерации,
     * переходит к следующей строке. При нахождении первой выгрышной строки
     * выходит из цикла и возвращает true.
     * @param soughtValue искомое значение: true - для Крестика, false - для Нолика.
     * @return true - в одной из строк есть выигрышная комбинация,
     * false - выигрышных комбинаций в строках нет.
     */
    private boolean checkRows(boolean soughtValue) {
        boolean result = false;
        int size = this.table.length;
        for (int row = 0; row != size && !result; row++) {
            result = true;
            for (int column = 0; column != size - 1 && result; column++) {
                boolean currentGap = this.table[row][column].hasMarkX() == this.table[row][column].hasMarkO();
                boolean nextGap = this.table[row][column + 1].hasMarkX() == this.table[row][column + 1].hasMarkO();
                boolean currentX = this.table[row][column].hasMarkX();
                boolean nextX = this.table[row][column + 1].hasMarkX();
                result = !currentGap && !nextGap
                        && currentX == soughtValue && currentX == nextX;
            }
        }
        return result;
    }

    /**
     * Ищет выигрышную комбинацию в столбцах для параметра soughtValue.
     * Проверяет в цикле столбцы. В каждом столбце для текущего и следующего
     * элемента проверяет, что они не пустые, равны друг дугу и искомому
     * значению soughtValue. Если условия не выполняются на какой-либо итерации,
     * переходит к следующему столбцу. При нахождении первого выгрышного столбца
     * выходит из цикла и возвращает true.
     * @param soughtValue искомое значение: true - для Крестика, false - для Нолика.
     * @return true - в одном из столбцов есть выигрышная комбинация,
     * false - выигрышных комбинаций в столбцах нет.
     */
    private boolean checkColumns(boolean soughtValue) {
        boolean result = false;
        int size = this.table.length;
        for (int column = 0; column != size && !result; column++) {
            result = true;
            for (int row = 0; row != size - 1 && result; row++) {
                boolean currentGap = this.table[row][column].hasMarkX() == this.table[row][column].hasMarkO();
                boolean nextGap = this.table[row + 1][column].hasMarkX() == this.table[row + 1][column].hasMarkO();
                boolean currentX = this.table[row][column].hasMarkX();
                boolean nextX = this.table[row + 1][column].hasMarkX();
                result = !currentGap && !nextGap
                        && currentX == soughtValue && currentX == nextX;
            }
        }
        return result;
    }

    /**
     * Ищет выигрышную комбинацию для параметра soughtValue в диагонали,
     * спускающейся слева направо. Для текущего и следующего
     * элемента проверяет, что они не пустые, равны друг дугу и искомому
     * значению soughtValue. Если условия не выполняются на какой-либо итерации,
     * выходит из цикла и возвращает false.
     * @param soughtValue искомое значение: true - для Крестика, false - для Нолика.
     * @return true - в диагонали есть выигрышная комбинация,
     * false - выигрышных комбинаций в диагонали нет.
     */
    private boolean checkLeftDiagonal(boolean soughtValue) {
        boolean result = true;
        int size = this.table.length - 1;
        for (int index = 0; index != size && result; index++) {
            boolean currentGap = this.table[index][index].hasMarkX() == this.table[index][index].hasMarkO();
            boolean nextGap = this.table[index + 1][index + 1].hasMarkX() == this.table[index + 1][index + 1].hasMarkO();
            boolean currentX = this.table[index][index].hasMarkX();
            boolean nextX = this.table[index + 1][index + 1].hasMarkX();
            result = !currentGap && !nextGap
                    && currentX == soughtValue && currentX == nextX;
        }
        return result;
    }

    /**
     * Ищет выигрышную комбинацию для параметра soughtValue в диагонали,
     * спускающейся справа налево. Для текущего и следующего
     * элемента проверяет, что они не пустые, равны друг дугу и искомому
     * значению soughtValue. Если условия не выполняются на какой-либо итерации,
     * выходит из цикла и возвращает false.
     * @param soughtValue искомое значение: true - для Крестика, false - для Нолика.
     * @return true - в диагонали есть выигрышная комбинация,
     * false - выигрышных комбинаций в диагонали нет.
     */
    private boolean checkRightDiagonal(boolean soughtValue) {
        boolean result = true;
        int size = this.table.length - 1;
        for (int i = 0, j = size; i != size && result; i++, j--) {
            boolean currentGap = this.table[i][j].hasMarkX() == this.table[i][j].hasMarkO();
            boolean nextGap = this.table[i + 1][j - 1].hasMarkX() == this.table[i + 1][j - 1].hasMarkO();
            boolean currentX = this.table[i][j].hasMarkX();
            boolean nextX = this.table[i + 1][j - 1].hasMarkX();
            result = !currentGap && !nextGap
                    && currentX == soughtValue && currentX == nextX;
        }
        return result;
    }
}