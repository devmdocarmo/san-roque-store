package com.application.sanroquestock.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.application.sanroquestock.R
import com.application.sanroquestock.model.BaseActivity

class SplashActivity : BaseActivity() {
    lateinit var logo : ImageView
    lateinit var progress : ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logo = findViewById(R.id.im_logo_app)
        progress = findViewById(R.id.progress_circular)
        logo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.smooth_appearance))
        progress.startAnimation(AnimationUtils.loadAnimation(this, R.anim.smooth_appearance))
        /*********Iniciar las BBDD **************/


        /*****************************************/
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }, 3000)
        /*val string = "My sensitive string that I want to encrypt"
        val bytes = string.toByteArray()
        val map: HashMap<String, ByteArray> = encryptBytes(bytes, "u2iht3w74tsi7tehxcbjn4")

        //Decryption test
        val decrypted = decryptData(map, "u2iht3w74tsi7tehxcbjn4")
        if (decrypted != null) {
            val decryptedString = String(decrypted)
            Log.e("MYAPP", "Decrypted String is : $decryptedString")*/
    }
}