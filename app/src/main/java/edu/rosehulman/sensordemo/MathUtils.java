package edu.rosehulman.sensordemo;

public class MathUtils {

    public static boolean withinDelta(float a, float b, float delta) {
        return Math.abs(a - b) <= delta;
    }
}
