package de.avpod;

/**
 * Created by apodznoev
 * date 09.11.2017.
 */
public class State {
    private final Coordinate coordinate;
    private final Direction direction;

    private State(Coordinate coordinate, Direction direction) {
        this.coordinate = coordinate;
        this.direction = direction;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Direction getDirection() {
        return direction;
    }

    public static State of(Coordinate coordinate, Direction direction) {
        return new State(coordinate, direction);
    }

    private State rotate(boolean clockDirection) {
        Direction newDirection;
        switch (direction) {
            case TOP:
                newDirection = clockDirection ? Direction.RIGHT : Direction.LEFT;
                break;
            case DOWN:
                newDirection = clockDirection ? Direction.LEFT : Direction.RIGHT;
                break;
            case LEFT:
                newDirection = clockDirection ? Direction.TOP : Direction.DOWN;
                break;
            case RIGHT:
                newDirection = clockDirection ? Direction.DOWN : Direction.TOP;
                break;
            default:
                throw new RuntimeException("Illegal direction:" + direction);
        }
        return new State(coordinate, newDirection);
    }

    public State rotateRight() {
        return rotate(true);
    }

    public State rotateLeft() {
        return rotate(false);
    }

    public State moveOneForward() {
        Coordinate newCoordinate;
        switch (direction) {
            case RIGHT:
                newCoordinate = Coordinate.of(coordinate.getX() + 1, coordinate.getY());
                break;
            case LEFT:
                newCoordinate = Coordinate.of(coordinate.getX() - 1, coordinate.getY());
                break;
            case TOP:
                newCoordinate = Coordinate.of(coordinate.getX(), coordinate.getY() + 1);
                break;
            case DOWN:
                newCoordinate = Coordinate.of(coordinate.getX(), coordinate.getY() - 1);
                break;
            default:
                throw new RuntimeException("Illegal direction: " + direction);
        }

        return new State(newCoordinate, direction);
    }
}
