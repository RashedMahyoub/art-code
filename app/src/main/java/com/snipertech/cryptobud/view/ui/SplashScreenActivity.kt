package com.snipertech.cryptobud.view.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.snipertech.cryptobud.R
import gr.net.maroulis.library.EasySplashScreen

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val config = EasySplashScreen(this@SplashScreenActivity)
            .withFullScreen()
            .withTargetActivity(MainActivity::class.java)
            .withSplashTimeOut(2000)
            .withBackgroundColor(Color.parseColor("#FFFFFF"))
            .withLogo(R.drawable.ic_launcher_foreground)


        val easySplashScreen = config.create()
        setContentView(easySplashScreen)
    }
}
