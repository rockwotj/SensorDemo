package edu.rosehulman.sensordemo.pickshake;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import edu.rosehulman.sensordemo.MathUtils;

/** A detector that waits for the device to be shook along the device's Y axis. */
public class ShakeDetector {

    private final SensorManager mManager;
    private final Sensor mSensor;
    private ShakeListener mListener;

    /** A listener for shake events. */
    public interface Listener {
        /** Called when a shake takes place. */
        void onShake();
    }

    public ShakeDetector(Context context) {
        mManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
    }

    private static class ShakeListener implements SensorEventListener {

        private static final float SHAKE_THRESHOLD = 4;
        private Listener listener;
        private float previousY;

        public ShakeListener(Listener listener) {
            this.listener = listener;
        }

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float y = sensorEvent.values[1];
            if (!MathUtils.withinDelta(y, previousY, SHAKE_THRESHOLD)) {
                listener.onShake();
            }
            previousY = y;
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
            // ignore
        }
    }

    public void registerListener(Listener listener) {
        mListener = new ShakeListener(listener);
        mManager.registerListener(mListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unregisterListener() {
        mManager.unregisterListener(mListener);
        mListener = null;
    }

}