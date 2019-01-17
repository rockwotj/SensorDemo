package edu.rosehulman.sensordemo.scrolltilt;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import edu.rosehulman.sensordemo.MathUtils;

/**
 * A detector for device "Tilts" which is the top or bottom of the device being higher or
 * lower than the other end of the device relative to the distance from the ground. This is similar
 * to, but not the same as rotation along the X axis as it will continue to callback as long as
 * there is a tilt.
 */
public class TiltDetector {

    private final SensorManager mManager;
    private final Sensor mSensor;
    private SensorEventListener mListener;

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
        mManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public void registerListener(final Listener listener) {
        mListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float y = sensorEvent.values[1];
                if (MathUtils.withinDelta(0f, y, 0.5f)) {
                    return;
                }
                listener.onTilt(y > 0 ? Direction.BOTTOM : Direction.TOP,
                        Math.abs(y) / SensorManager.GRAVITY_EARTH);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
                // ignore
            }
        };
        mManager.registerListener(mListener, mSensor, SensorManager.SENSOR_DELAY_UI);
    }

    public void unregisterListener() {
        mManager.unregisterListener(mListener);
        mListener = null;
    }

}