package de.avpod;

import javax.swing.*;
import java.awt.*;

/**
 * Created by apodznoev
 * date 09.11.2017.
 */
public class BoardPanel extends JFrame {
    private final GridPane grid;

    public BoardPanel(Coordinate startPosition, int maxSteps) {
        super("Ant's adventures");
        pack();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int size = dimension.height / 2;
        setSize(new Dimension(size, size));
        grid = new GridPane(size, startPosition, maxSteps);
        add(grid);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void writeColor(Coordinate coordinate, SquareColor squareColor) {
        grid.markCell(coordinate.getX(), coordinate.getY(), squareColor);
        repaint();
    }

    private static class GridPane extends JPanel {

        private final boolean[][] cells;
        private final Coordinate startPosition;
        private final int maxSteps;
        private final double pixelSize;
        private final int maxOffset;

        public GridPane(int size, Coordinate startPosition, int maxSteps) {
            super(new GridLayout(size, size, 0, 0));
            this.startPosition = startPosition;
            this.maxSteps = maxSteps;
            int xOffset = Math.abs(startPosition.getX());
            int yOffset = Math.abs(startPosition.getY());
            this.maxOffset = Math.max(xOffset, yOffset);
            this.pixelSize = getWidth() / (maxSteps + maxOffset);
            cells = new boolean[maxOffset][maxOffset];
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            redrawBoard(g2d);
            g2d.dispose();
        }

        public void markCell(int row, int column, SquareColor squareColor) {

        }

        private void redrawBoard(Graphics2D g2d) {
            int width = getWidth();
            int height = getHeight();
            int pixelSize = width / (maxSteps + maxOffset);
            Rectangle cell = new Rectangle(0, 0, pixelSize, pixelSize);
            int row;
            int column = 0;
            int rowsCount = height / pixelSize;
            int columnsCount = width / pixelSize;

            while (column  <= columnsCount) {
                row = 0;
                while (row  <= rowsCount) {
                    g2d.translate(row * pixelSize, column * pixelSize);
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(0, 0, pixelSize, pixelSize);
                    g2d.setColor(Color.BLACK);
                    g2d.draw(cell);
                    g2d.drawString("x=" + row + ",y=" + column, 0, 10);
                    g2d.translate(-row * pixelSize, -column * pixelSize);
                    row++;
                }
                column++;
            }
            g2d.setColor(Color.BLACK);
            g2d.drawLine(width / 2, height, width / 2, 0);
            g2d.drawLine(0, height / 2, width, height / 2);

        }


    }
}
