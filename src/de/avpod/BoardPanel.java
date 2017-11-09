package de.avpod;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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
        grid.markCell(coordinate, squareColor);
        repaint();
    }

    private static class GridPane extends JPanel {

        private final Map<Coordinate, SquareColor> cells = new HashMap<>();
        private final Coordinate startPosition;
        private final int maxSteps;
        private final int maxOffset;

        public GridPane(int size, Coordinate startPosition, int maxSteps) {
            super(new GridLayout(size, size, 0, 0));
            this.startPosition = startPosition;
            this.maxSteps = maxSteps;
            int xOffset = Math.abs(startPosition.getX());
            int yOffset = Math.abs(startPosition.getY());
            this.maxOffset = Math.max(xOffset, yOffset);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            redrawBoard(g2d);
            g2d.dispose();
        }

        public void markCell(Coordinate coordinate, SquareColor squareColor) {
            cells.put(coordinate, squareColor);
        }

        private void redrawBoard(Graphics2D g2d) {
            int width = getWidth();
            int height = getHeight();
            int min = Math.min(width, height);
            int pixelSize = Math.max(1,min / (maxSteps + maxOffset));
            int rowsCount = min / pixelSize;
            int columnsCount = min / pixelSize;
            g2d.translate(rowsCount / 2 * pixelSize, columnsCount / 2 * pixelSize);

            for (int i = -rowsCount / 2; i < rowsCount / 2; i++) {
                for (int j = -columnsCount / 2; j < columnsCount / 2; j++) {
                    SquareColor color = cells.getOrDefault(Coordinate.of(i,j), SquareColor.WHITE);
                    drawCell(i, j, pixelSize, color == SquareColor.WHITE ? Color.WHITE : Color.BLACK, g2d);
                }
            }

            g2d.setColor(Color.RED);
            g2d.drawLine(-min / 2, 0, min / 2, 0);
            g2d.drawLine(0, -min / 2, 0, min / 2);

        }

        private void drawCell(int row, int column, int size, Color fillColor, Graphics2D g2d) {
            Rectangle cell = new Rectangle(0, 0, size, size);
            g2d.translate(row * size, -column * size);
            g2d.setColor(fillColor);
            g2d.fillRect(0, 0, size, size);
//            g2d.setColor(Color.BLACK);
            g2d.draw(cell);
//            g2d.drawString("x=" + row + ",y=" + column, 0, 10);
            g2d.translate(-row * size, column * size);
        }


    }
}
