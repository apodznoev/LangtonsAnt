package de.avpod;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by apodznoev
 * date 09.11.2017.
 */
public class Board {
    private final Map<Coordinate, SquareColor> state = new HashMap<>();

    public static Board emptyBoard() {
        return new Board();
    }

    public SquareColor readColor(Coordinate coordinate) {
        return state.computeIfAbsent(coordinate, c -> SquareColor.WHITE);
    }

    public SquareColor invertColor(Coordinate coordinate) {
        return state.compute(coordinate,(c, squareColor) -> squareColor.invert());
    }
}
