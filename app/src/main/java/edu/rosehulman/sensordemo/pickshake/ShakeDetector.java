package edu.rosehulman.sensordemo.pickshake;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.time.Duration;

import edu.rosehulman.sensordemo.MathUtils;

import static java.util.Objects.requireNonNull;

/** A detector that waits for the device to be shook along the device's Y axis. */
public class ShakeDetector {

    private static final float SHAKE_THRESHOLD = 4;
    private static final long DEBOUNCE_TIME = 1_000_000_000;

    private final SensorManager mManager;
    private final Sensor mSensor;

    private SensorEventListener mListener;

    /** A listener for shake events. */
    public interface Listener {
        /** Called when a shake takes place. */
        void onShake();
    }

    private static class ShakeListener implements SensorEventListener {

        private final Listener listener;
        private float previousY;

        private long lastFiredTimestamp;

        public ShakeListener(Listener listener) {
            this.listener = listener;
        }

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float y = sensorEvent.values[1];
            if (!MathUtils.withinDelta(y, previousY, SHAKE_THRESHOLD)
                    && lastFiredTimestamp + DEBOUNCE_TIME < sensorEvent.timestamp) {
                lastFiredTimestamp = sensorEvent.timestamp;
                listener.onShake();
            }
            previousY = y;
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
            // Ignore.
        }
    }

    public ShakeDetector(Context context) {
        mManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensor = requireNonNull(mManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION));
    }

    public void registerListener(final Listener listener) {
        mListener = new ShakeListener(listener);
        mManager.registerListener(mListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unregisterListener() {
        mManager.unregisterListener(mListener);
        mListener = null;
    }

}