package org.karpukhin.renju;

/**
 * @author Pavel Karpukhin
 * @since 02.11.12
 */
public interface RenjuModel {

    int EMPTY = 0;
    int BLACK = 1;
    int WHITE = 2;

    int getNumberOfRows();

    int getNumberOfColumns();

    int getValueAt(int row, int column);

    void setValueAt(int row, int column, int value);

    void makeNextStep(int row, int column);
}
