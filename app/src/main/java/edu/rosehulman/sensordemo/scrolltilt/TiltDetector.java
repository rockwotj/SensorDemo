package edu.rosehulman.sensordemo.scrolltilt;

import android.content.Context;

/**
 * A detector for device "Tilts" which is the top or bottom of the device being higher or
 * lower than the other end of the device relative to the distance from the ground. This is similar
 * to, but not the same as rotation along the X axis as it will continue to callback as long as
 * there is a tilt.
 */
public class TiltDetector {

    private Listener mListener;

    /** The direction of the tilt. */
    public enum Direction {
        /** The <i>top</i> of the device is <b>lower</b> than the bottom. */
        TOP,
        /** The <i>bottom</i> of the device is <b>lower</b> than the top. */
        BOTTOM
    }

    /** A listener for tilt events. */
    public interface Listener {
        /**
         * Called when a tilt of the device happens in a general direction - also include the
         * magitude of the tilt on the scale of [0, 1].
         */
        void onTilt(Direction direction, double magnitude);
    }

    public TiltDetector(Context context) {
        // TODO: Implement me!
    }

    public void registerListener(Listener listener) {
        // TODO: Implement me!
    }

    public void unregisterListener() {
        // TODO: Implement me!
    }

}