package edu.rosehulman.sensordemo.scrolltilt;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.widget.NestedScrollView;

import edu.rosehulman.sensordemo.R;

public class ScrollTiltActivity extends Activity {

    private NestedScrollView mScrollView;
    private TiltDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_tilt);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        mScrollView = findViewById(R.id.scroll_view);
        // mScrollView.smoothScrollBy(dx, dy);
        mDetector = new TiltDetector(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDetector.registerListener(new TiltDetector.Listener() {
            static final double SCROLL_MULTIPLIER = 20;

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
        mDetector.unregisterListener();
    }
}
