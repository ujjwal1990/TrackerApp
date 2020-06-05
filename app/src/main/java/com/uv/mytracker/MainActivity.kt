package com.uv.mytracker

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.leinardi.android.speeddial.SpeedDialActionItem
import com.uv.mytracker.base.showToast
import com.uv.mytracker.reminders.SleepReminderFragment
import com.uv.mytracker.reminders.WaterReminderFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initSpeedDial()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initSpeedDial() {
        speedDialView.overlayLayout = overlay
        speedDialView.addActionItem(
            addSpeedDialActionItems(
                R.id.fab_water,
                R.drawable.ic_water,
                getString(R.string.water)
            )
        )
        speedDialView.addActionItem(
            addSpeedDialActionItems(
                R.id.fab_medicine,
                R.drawable.ic_medicine,
                getString(R.string.medicine)
            )
        )
        speedDialView.addActionItem(
            addSpeedDialActionItems(
                R.id.fab_workout,
                R.drawable.ic_workout,
                getString(R.string.workout)
            )
        )
        speedDialView.addActionItem(
            addSpeedDialActionItems(
                R.id.fab_sleep,
                R.drawable.ic_sleep,
                getString(R.string.sleep)
            )
        )
        speedDialView.addActionItem(
            addSpeedDialActionItems(
                R.id.fab_wakeup,
                R.drawable.ic_awake,
                getString(R.string.wakeup)
            )
        )
        setOnActionSelectedListener()
    }

    private fun addSpeedDialActionItems(
        @IdRes id: Int,
        @DrawableRes fabImageResource: Int,
        @Nullable labelText: String
    ): SpeedDialActionItem {
        return SpeedDialActionItem.Builder(id, fabImageResource)
            .setLabel(labelText).setFabSize(FloatingActionButton.SIZE_MINI)
            .setFabBackgroundColor(
                ResourcesCompat.getColor(
                    resources,
                    R.color.material_white_1000,
                    theme
                )
            )
            .create()
    }

    private fun setOnActionSelectedListener() {
        speedDialView.setOnActionSelectedListener OnActionSelectedListener@{ actionItem ->
            when (actionItem.id) {
                R.id.fab_water -> {
                    WaterReminderFragment().show(supportFragmentManager, "ReminderFragment")
                    speedDialView.close()
                    return@OnActionSelectedListener false
                }
                R.id.fab_medicine -> showToast(actionItem.getLabel(this@MainActivity) + " clicked!")
                R.id.fab_sleep -> {
                    SleepReminderFragment().show(supportFragmentManager, "SleepReminderFragment")
                    speedDialView.close()
                    return@OnActionSelectedListener false
                }
                R.id.fab_wakeup -> showToast(actionItem.getLabel(this@MainActivity) + " clicked!")
                R.id.fab_workout -> showToast(actionItem.getLabel(this@MainActivity) + " clicked!")
                //                    return@OnActionSelectedListener false
            }
            true // To keep the Speed Dial open
        }
    }
}
