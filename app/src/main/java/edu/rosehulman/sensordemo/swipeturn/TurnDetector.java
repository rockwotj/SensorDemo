package edu.rosehulman.sensordemo.swipeturn;

import android.app.Context;

/** A detector that waits for a "Turn" or a rotation along the Y axis. */
public class TurnDetector {

    /** The direction of the turn as if you are looking at the device screen. */
    public enum Direction { LEFT, RIGHT }

    /** A listener that registers for turn events. */
    public interface Listener {
        /** Called when a turn takes place. */
        void onTurn(Direction direction);
    }

    public TurnDetector(Context context) {
        // TODO: Implement me!
    }

    public void registerListener(Direction direction) {
        // TODO: Implement me!
    }

    public void unregisterListener() {
        // TODO: Implement me!
    }
}