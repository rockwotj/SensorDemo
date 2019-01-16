package edu.rosehulman.sensordemo.scrolltilt;

import android.app.Context;

/**
 * A detector for device "Tilts" which is the top or bottom of the device being higher or
 * lower than the other end of the device relative to the distance from the ground. This is similar
 * to, but not the same as rotation along the X axis.
 */
public class TiltDetector {

    private mListener listener;

    /** The direction of the tilt. */
    public enum Direction {
        /** The <i>top</i> of the device is <b>lower</b> than the bottom. */
        TOP,
        /** The <i>bottom</i> of the device is <b>lower</b> than the top. */
        BOTTOM
    }

    /** A listener for tilt events. */
    public interface Listener {
        /** Called when a tilt of the device takes place. */
        void onTilt(Direction direction);
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