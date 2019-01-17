package edu.rosehulman.sensordemo.pickshake;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Random;

import edu.rosehulman.sensordemo.R;

public class PickShakeActivity extends Activity {

    private Spinner mSpinner;
    private ShakeDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_shake);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        mSpinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mDetector = new ShakeDetector(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDetector.registerListener(new ShakeDetector.Listener() {
            @Override
            public void onShake() {
                mSpinner.setSelection(new Random().nextInt(mSpinner.getCount()));
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDetector.unregisterListener();
    }
}
