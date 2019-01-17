package edu.rosehulman.sensordemo.scrolltilt;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.widget.NestedScrollView;

import edu.rosehulman.sensordemo.R;

public class ScrollTiltActivity extends Activity {

    private NestedScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_tilt);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        mScrollView = findViewById(R.id.scroll_view);
        // mScrollView.smoothScrollBy(dx, dy);
    }
}
