package com.application.sanroquestock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ProgressBar

class SplashActivity : AppCompatActivity() {
    lateinit var logo : ImageView
    lateinit var progress : ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logo = findViewById(R.id.im_logo_app)
        progress = findViewById(R.id.progress_circular)
        logo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.smooth_appearance))
        progress.startAnimation(AnimationUtils.loadAnimation(this, R.anim.smooth_appearance))
    }
}