package ru.job4j.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {
        return this.checkRows(true)
                || this.checkColumns(true)
                || this.checkLeftDiagonal(true)
                || this.checkRightDiagonal(true);

    }

    public boolean isWinnerO() {
        return this.checkRows(false)
                || this.checkColumns(false)
                || this.checkLeftDiagonal(false)
                || this.checkRightDiagonal(false);
    }

    public boolean hasGap() {
        boolean result = false;
        int size = this.table.length;
        for (int i = 0; i != size; i++) {
            for (int j = 0; j != size; j++) {
                result = this.table[i][j].hasMarkX() == this.table[i][j].hasMarkO();
                if (result) {
                    return result;
                }
            }
        }
        return result;
    }

    private boolean checkRows(boolean soughtValue) {
        boolean result = false;
        int size = this.table.length;
        for (int row = 0; row != size; row++) {
            for (int column = 0; column != size - 1; column++) {
                boolean currentGap = this.table[row][column].hasMarkX() == this.table[row][column].hasMarkO();
                boolean nextGap = this.table[row][column + 1].hasMarkX() == this.table[row][column + 1].hasMarkO();
                boolean currentX = this.table[row][column].hasMarkX();
                boolean nextX = this.table[row][column + 1].hasMarkX();
                result = !currentGap && !nextGap && currentX == nextX;
                if (!result) {
                    break;
                }
            }
            if (result) {
                result = this.table[row][0].hasMarkX() == soughtValue;
                break;
            }
        }
        return result;
    }

    private boolean checkColumns(boolean soughtValue) {
        boolean result = false;
        int size = this.table.length;
        for (int column = 0; column != size; column++) {
            for (int row = 0; row != size - 1; row++) {
                boolean currentGap = this.table[row][column].hasMarkX() == this.table[row][column].hasMarkO();
                boolean nextGap = this.table[row + 1][column].hasMarkX() == this.table[row + 1][column].hasMarkO();
                boolean currentX = this.table[row][column].hasMarkX();
                boolean nextX = this.table[row + 1][column].hasMarkX();
                result = !currentGap && !nextGap && currentX == nextX;
                if (!result) {
                    break;
                }
            }
            if (result) {
                result = this.table[0][column].hasMarkX() == soughtValue;
                break;
            }
        }
        return result;
    }

    private boolean checkLeftDiagonal(boolean soughtValue) {
        boolean result = false;
        int size = this.table.length - 1;
        for (int index = 0; index != size; index++) {
            boolean currentGap = this.table[index][index].hasMarkX() == this.table[index][index].hasMarkO();
            boolean nextGap = this.table[index + 1][index + 1].hasMarkX() == this.table[index + 1][index + 1].hasMarkO();
            boolean currentX = this.table[index][index].hasMarkX();
            boolean nextX = this.table[index + 1][index + 1].hasMarkX();
            result = !currentGap && !nextGap && currentX == nextX;
            if (!result) {
                break;
            }
        }
        if (result) {
            result = this.table[0][0].hasMarkX() == soughtValue;
        }
        return result;
    }

    private boolean checkRightDiagonal(boolean soughtValue) {
        boolean result = false;
        int size = this.table.length - 1;
        for (int i = 0, j = size; i != size; i++, j--) {
            boolean currentGap = this.table[i][j].hasMarkX() == this.table[i][j].hasMarkO();
            boolean nextGap = this.table[i + 1][j - 1].hasMarkX() == this.table[i + 1][j - 1].hasMarkO();
            boolean currentX = this.table[i][j].hasMarkX();
            boolean nextX = this.table[i + 1][j - 1].hasMarkX();
            result = !currentGap && !nextGap && currentX == nextX;
            if (!result) {
                break;
            }
        }
        if (result) {
            result = this.table[0][size].hasMarkX() == soughtValue;
        }
        return result;
    }
}