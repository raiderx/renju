package org.karpukhin.renju;

/**
 * @author Pavel Karpukhin
 * @since 02.11.12
 */
public class DefaultRenjuModel implements RenjuModel {

    public static final int DEFAULT_NUMBER_OF_ROWS = 5;
    public static final int DEFAULT_NUMBER_OF_COLUMNS = 5;

    private final int numberOfStones = 5;
    private final int numberOfRows;
    private final int numberOfColumns;
    private int nextValue = BLACK;

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
        if (table[row][column] != BLACK && table[row][column] != WHITE) {
            table[row][column] = nextValue;
            checkTable(row, column, nextValue);
            if (nextValue == BLACK) {
                nextValue = WHITE;
            } else {
                nextValue = BLACK;
            }
        }
    }

    public int checkDst(int row, int column, int dx, int dy, int value) {
        int count = 0;
        while (column >= 0 && column < numberOfColumns && row >= 0 && row < numberOfRows && table[row][column] == value) {
            ++count;
            column += dx;
            row += dy;
        }
        return count;
    }

    public int checkLine(int row, int column, int dx, int dy, int value) {
        int count = checkDst(row, column, dx, dy, value);
        dx = -dx;
        dy = -dy;
        count += checkDst(row + dy, column + dx, dx, dy, value);
        return count;
    }

    public void checkTable(int row, int column, int value) {
        if (checkLine(row, column, 1, 0, value) >= numberOfStones) {
            System.out.println("X You win");
        }
        if (checkLine(row, column, 0, 1, value) >= numberOfStones) {
            System.out.println("Y You win");
        }
        if (checkLine(row, column, 1, 1, value) >= numberOfStones) {
            System.out.println("A You win");
        }
        if (checkLine(row, column, 1, -1, value) >= numberOfStones) {
            System.out.println("B You win");
        }
    }
}
