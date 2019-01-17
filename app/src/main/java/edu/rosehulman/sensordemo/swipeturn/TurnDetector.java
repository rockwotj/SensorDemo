package edu.rosehulman.sensordemo.swipeturn;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import edu.rosehulman.sensordemo.MathUtils;

/** A detector that waits for a "Turn" or a rotation along the Y axis. */
public class TurnDetector {

    private final SensorManager mManager;
    private final Sensor mSensor;
    private SensorEventListener mListener;

    /** The direction of the turn as if you are looking at the device screen. */
    public enum Direction { LEFT, RIGHT }

    /** A listener that registers for turn events. */
    public interface Listener {
        /** Called when a turn takes place. */
        void onTurn(Direction direction);
    }

    public TurnDetector(Context context) {
        mManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    public void registerListener(final Listener listener) {
        mListener = new SensorEventListener() {

            private static final long DEBOUNCE_INTERVAL = 1_000_000_000;

            public long lastTimestamp;

            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float yRotation = sensorEvent.values[1];
                if (!MathUtils.withinDelta(0f, yRotation, 2f)
                        && lastTimestamp + DEBOUNCE_INTERVAL < sensorEvent.timestamp) {
                    lastTimestamp = sensorEvent.timestamp;
                    listener.onTurn(yRotation < 0 ? Direction.LEFT : Direction.RIGHT);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
                // ignore
            }
        };
        mManager.registerListener(mListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unregisterListener() {
        mManager.unregisterListener(mListener);

    }
}