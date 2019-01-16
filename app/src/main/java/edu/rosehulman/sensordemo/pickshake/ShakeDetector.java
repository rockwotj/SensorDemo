package edu.rosehulman.sensordemo.pickshake;

import android.app.Context;

/** A detector that waits for the device to be shook along the device's Y axis. */
public class ShakeDetector {

    private Listener mListener;

    /** A listener for shake events. */
    public interface Listener {
        /** Called when a shake takes place. */
        void onShake();
    }

    public ShakeDetector(Context context) {
        // TODO: Implement me!
    }

    public void registerListener(Listener listener) {
        // TODO: Implement me!
    }

    public void unregisterListener() {
        // TODO: Implement me!
    }

}