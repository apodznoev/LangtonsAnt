package de.avpod;

public class Main {
    private static BoardPanel boardPanel;

    public static void main(String... args) {
        if (args.length < 4) {
            throw new RuntimeException("Illegal input, expected: X Y t|d|r|l StepsCount");
        }
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        Coordinate coordinate = Coordinate.of(x, y);
        Direction direction = Direction.parse(args[2]);
        State state = State.of(coordinate, direction);
        int stepsCount = Integer.parseInt(args[3]);
        boardPanel = new BoardPanel(coordinate, stepsCount);
        doProgram(state, stepsCount);
    }

    private static void doProgram(State state, int stepsCount) {
        Board board = Board.emptyBoard();
        for (int i = 0; i < stepsCount; i++) {
            SquareColor squareColor = board.readColor(state.getCoordinate());
            switch (squareColor) {
                case WHITE:
                    squareColor = board.invertColor(state.getCoordinate());
                    outputColor(state.getCoordinate(), squareColor);
                    state = state.rotateRight().moveOneForward();

                    break;
                case BLACK:
                    squareColor = board.invertColor(state.getCoordinate());
                    outputColor(state.getCoordinate(), squareColor);
                    state = state.rotateLeft().moveOneForward();
                    break;
            }
            outputLocation(state.getCoordinate());
            try {
                Thread.sleep(Math.max(1, stepsCount / 10000));
            } catch (InterruptedException ignored) {
            }
        }
    }

    private static void outputColor(Coordinate coordinate, SquareColor squareColor) {
        boardPanel.writeColor(coordinate, squareColor);
    }

    private static void outputLocation(Coordinate coordinate) {
        System.out.printf("%d %d \n", coordinate.getX(), coordinate.getY());
    }
}
