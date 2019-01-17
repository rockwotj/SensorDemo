package edu.rosehulman.sensordemo;

public class MathUtils {

    /** Return true if {@code a} and {@code b} are within {@code delta} of each other. */
    public static boolean withinDelta(float a, float b, float delta) {
        return Math.abs(a - b) < delta;
    }
}
