package de.avpod;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by apodznoev
 * date 09.11.2017.
 */
public enum Direction {
    TOP("t"),
    DOWN("d"),
    LEFT("l"),
    RIGHT("r");


    private final String shortRepresentation;

    Direction(String s) {
        shortRepresentation = s;
    }

    public static Direction parse(String s){
        return Arrays.stream(values())
                .filter(en -> en.shortRepresentation.equals(s))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cannot find enum for " + s));
    }
}
