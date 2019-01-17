package edu.rosehulman.sensordemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import edu.rosehulman.sensordemo.pickshake.PickShakeActivity
import edu.rosehulman.sensordemo.scrolltilt.ScrollTiltActivity
import edu.rosehulman.sensordemo.swipeturn.SwipeTurnActivity

class ChooserActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chooser)
        findViewById<Button>(R.id.pick_shake_btn).setOnClickListener {
            startActivity(Intent(this, PickShakeActivity::class.java))
        }
        findViewById<Button>(R.id.scroll_tilt_btn).setOnClickListener {
            startActivity(Intent(this, ScrollTiltActivity::class.java))
        }
        findViewById<Button>(R.id.swipe_turn_btn).setOnClickListener {
            startActivity(Intent(this, SwipeTurnActivity::class.java))
        }
    }
}
