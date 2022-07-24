package com.mlpozdeev.questmap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    companion object {
        private const val BASE_TIMER_TIME_MILLIS = 10000L
        private const val TIMER_TIME_STEP_MILLIS = 1000L
    }

    private lateinit var timeTextView: TextView
    private lateinit var startTimerButton: Button
    private lateinit var mapView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        timeTextView = findViewById(R.id.time_text_view)
        startTimerButton = findViewById(R.id.start_timer_button)
        mapView = findViewById(R.id.map_view)
        var timeLeft = getTimeLeft(BASE_TIMER_TIME_MILLIS)
        timeTextView.text = timeLeft
        val timer = object : CountDownTimer(BASE_TIMER_TIME_MILLIS, TIMER_TIME_STEP_MILLIS) {

            override fun onTick(millisUntilFinished: Long) {
                timeLeft = getTimeLeft(millisUntilFinished)
                timeTextView.text = timeLeft
            }

            override fun onFinish() {
                mapView.setBackgroundColor(getColor(R.color.white))
                startTimerButton.isVisible = true
            }

        }
        startTimerButton.setOnClickListener {
            timer.start()
            startTimerButton.isVisible = false
        }
    }

    private fun getTimeLeft(timeLeftMillis: Long) = "${timeLeftMillis / 60000}:${timeLeftMillis % 60000 / 1000}"

}
