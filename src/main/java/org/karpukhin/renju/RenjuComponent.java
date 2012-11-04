package org.karpukhin.renju;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Pavel Karpukhin
 * @since 02.11.12
 */
public class RenjuComponent extends JComponent {

    private RenjuModel renjuModel;

    private static final int minColumnWidth = 30;
    private static final int minRowHeight = 30;
    private static final int xFieldOffset = 18;
    private static final int yFieldOffset = 18;

    public RenjuComponent() {
        this(new DefaultRenjuModel(15, 15));
    }

    public RenjuComponent(RenjuModel renjuModel) {
        this.renjuModel = renjuModel;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = convertXtoColumn(e.getX());
                int row = convertYtoRow(e.getY());
                RenjuComponent.this.makeNextStep(row, column);
            }
        });
        this.setMinimumSize(new Dimension(
                xFieldOffset + minColumnWidth * this.renjuModel.getNumberOfColumns(),
                yFieldOffset + minRowHeight * this.renjuModel.getNumberOfRows()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Color defaultColor = g.getColor();
        FontMetrics defaultFontMetrics = g.getFontMetrics();

        g.setColor(new Color(0xFF, 0xDE, 0xAD));
        g.fillRoundRect(xFieldOffset, 0, getWidth() - xFieldOffset - 1, getHeight() - yFieldOffset, 10, 10);
        g.setColor(Color.BLACK);
        g.drawRoundRect(xFieldOffset, 0, getWidth() - xFieldOffset - 1, getHeight() - yFieldOffset, 10, 10);

        double columnWidth = getColumnWidth();
        double rowHeight = getRowHeight();
        for (int i = 0; i < renjuModel.getNumberOfRows(); ++i) {
            int x1 = xFieldOffset + (int)(columnWidth / 2);
            int x2 = xFieldOffset + (int)(columnWidth / 2 + 14 * columnWidth);
            int y = (int)(rowHeight / 2 + i * rowHeight);
            g.drawLine(x1, y, x2, y);
            String label = Integer.toString(renjuModel.getNumberOfRows() - i);
            g.drawString(label, xFieldOffset - 4 - defaultFontMetrics.stringWidth(label), y + defaultFontMetrics.getHeight() / 2);
        }
        for (int i = 0; i < renjuModel.getNumberOfColumns(); ++i) {
            int x = xFieldOffset + (int)(columnWidth / 2 + i * columnWidth);
            int y1 = 1 + (int)(rowHeight / 2);
            int y2 = (int)(rowHeight / 2 + 14 * rowHeight) - 1;
            g.drawLine(x, y1, x, y2);
            String label = String.valueOf((char) ('a' + i));
            g.drawString(label, x- defaultFontMetrics.stringWidth(label) / 2, (int)(15 * rowHeight) + defaultFontMetrics.getHeight());
        }
        int ovalWidth = (int)(columnWidth - 4);
        int ovalHeight = (int)(rowHeight - 4);
        for (int i = 0; i < renjuModel.getNumberOfRows(); ++i) {
            for (int j = 0; j < renjuModel.getNumberOfColumns(); ++j) {
                if (renjuModel.getValueAt(i, j) == 1) {
                    g.setColor(Color.WHITE);
                    g.fillOval(xFieldOffset + (int) (j * columnWidth) + 2, (int) (i * rowHeight) + 2, ovalWidth, ovalHeight);
                    g.setColor(Color.BLACK);
                    g.drawOval(xFieldOffset + (int) (j * columnWidth) + 2, (int) (i * rowHeight) + 2, ovalWidth, ovalHeight);
                } else if (renjuModel.getValueAt(i, j) == 2) {
                    g.setColor(Color.BLACK);
                    g.fillOval(xFieldOffset + (int) (j * columnWidth) + 2, (int) (i * rowHeight) + 2, ovalWidth, ovalHeight);
                }
            }
        }
        g.setColor(defaultColor);
    }

    private double getColumnWidth() {
        return ((double)this.getWidth() - xFieldOffset - 1) / renjuModel.getNumberOfColumns();
    }

    private double getRowHeight() {
        return ((double)this.getHeight() - yFieldOffset - 1) / renjuModel.getNumberOfRows();
    }

    private int convertXtoColumn(int x) {
        return (int)((x - xFieldOffset) / getColumnWidth());
    }

    private int convertYtoRow(int y) {
        return (int)(y / getRowHeight());
    }

    private void makeNextStep(int row, int column) {
        renjuModel.makeNextStep(row, column);
        repaint();
    }
}
