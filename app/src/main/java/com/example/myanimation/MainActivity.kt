package com.example.myanimation

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_start.setOnClickListener {
            topAnimation()
        }
        road_number.setRoadData(24,23,67)
    }

    private fun topAnimation() {
        val topAn = ValueAnimator.ofFloat(0f, 100f, 0f)
        topAn.addUpdateListener {
            val value = it.animatedValue
            tv_test.translationY = value as Float
        }
        topAn.duration = 1000
        topAn.start()
    }
}
