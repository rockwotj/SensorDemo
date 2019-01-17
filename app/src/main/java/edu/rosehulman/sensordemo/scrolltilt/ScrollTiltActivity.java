package edu.rosehulman.sensordemo.scrolltilt;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.widget.NestedScrollView;

import edu.rosehulman.sensordemo.R;

public class ScrollTiltActivity extends Activity {

    private final static int SCROLL_MULTIPLIER = 20;

    private NestedScrollView mScrollView;
    private TiltDetector mTiltDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_tilt);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        mScrollView = findViewById(R.id.scroll_view);
        mTiltDetector = new TiltDetector(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTiltDetector.registerListener(new TiltDetector.Listener() {
            @Override
            public void onTilt(TiltDetector.Direction direction, double magnitude) {
                int dy = 0;
                switch (direction) {
                    case TOP:
                        dy = (int) (magnitude * SCROLL_MULTIPLIER);
                        break;
                    case BOTTOM:
                        dy = (int) (magnitude * -SCROLL_MULTIPLIER);
                        break;
                }
                mScrollView.smoothScrollBy(0, dy);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mTiltDetector.unregisterListener();
    }
}
