package org.karpukhin.renju;

/**
 * @author Pavel Karpukhin
 * @since 02.11.12
 */
public class DefaultRenjuModel implements RenjuModel {

    public static final int DEFAULT_NUMBER_OF_ROWS = 5;
    public static final int DEFAULT_NUMBER_OF_COLUMNS = 5;

    private int numberOfRows;
    private int numberOfColumns;
    private int nextValue = 1;

    private int table[][];

    public DefaultRenjuModel() {
        this(DEFAULT_NUMBER_OF_ROWS, DEFAULT_NUMBER_OF_COLUMNS);
    }

    public DefaultRenjuModel(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;

        table = new int[numberOfRows][];
        for (int i = 0; i < numberOfRows; ++i) {
            table[i] = new int[numberOfColumns];
        }
    }

    @Override
    public int getNumberOfRows() {
        return numberOfRows;
    }

    @Override
    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    @Override
    public int getValueAt(int row, int column) {
        return table[row][column];
    }

    @Override
    public void setValueAt(int row, int column, int value) {
        table[row][column] = value;
    }

    @Override
    public void makeNextStep(int row, int column) {
        if (table[row][column] != 1 && table[row][column] != 2) {
            table[row][column] = nextValue;
            checkTable(row, column, nextValue);
            if (nextValue == 1) {
                nextValue = 2;
            } else {
                nextValue = 1;
            }
        }
    }

    public int checkDst(int row, int column, int dx, int dy, int value) {
        int cx = column, cy = row;
        int count = 0;
        while (cx >= 0 && cx < numberOfColumns && cy >= 0 && cy < numberOfRows && table[cy][cx] == value) {
            ++count;
            cx += dx;
            cy += dy;
        }
        dx = -dx;
        dy = -dy;
        cx = column + dx;
        cy = row + dy;
        while (cx >= 0 && cx < numberOfColumns && cy >= 0 && cy < numberOfRows && table[cy][cx] == value) {
            ++count;
            cx += dx;
            cy += dy;
        }
        return count;
    }

    public void checkTable(int row, int column, int value) {
        if (checkDst(row, column, 1, 0, value) >= 5) {
            System.out.println("X You win");
        }
        if (checkDst(row, column, 0, 1, value) >= 5) {
            System.out.println("Y You win");
        }
        if (checkDst(row, column, 1, 1, value) >= 5) {
            System.out.println("A You win");
        }
        if (checkDst(row, column, 1, -1, value) >= 5) {
            System.out.println("B You win");
        }
    }
}
