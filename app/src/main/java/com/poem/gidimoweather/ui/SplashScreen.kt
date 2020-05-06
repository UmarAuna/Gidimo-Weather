package com.poem.gidimoweather.ui

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.animation.BounceInterpolator
import com.poem.gidimoweather.R
import com.poem.gidimoweather.databinding.ActivitySplashScreenBinding
import java.util.*

class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    var lokaci: Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val anim = AnimatorInflater.loadAnimator(
            this,
            R.animator.scale_animation
        ) as AnimatorSet
        anim.setTarget(binding.titleLogo)
        anim.interpolator = BounceInterpolator()
        anim.start()

        val gudu = Timer()
        val ShowSplash: TimerTask = object : TimerTask() {
            override fun run() {
                finish()
                val nextIntent = Intent(applicationContext, NewLocation::class.java)
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                startActivity(nextIntent)
            }
        }
        gudu.schedule(ShowSplash,lokaci)
    }


}
