package de.avpod;

/**
 * Created by apodznoev
 * date 09.11.2017.
 */
public class Coordinate {
    private final int x;
    private final int y;

    private Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public static Coordinate of(int x, int y){
        return new Coordinate(x,y);
    }
}
