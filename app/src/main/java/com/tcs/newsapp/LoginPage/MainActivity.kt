package com.tcs.newsapp.LoginPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.view.WindowManager
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    val TIMER_NAME="SettingUp"
    val TIMER_DELAY:Long=5000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)


newsImage.startAnimation(AnimationUtils.loadAnimation(this,R.anim.top_animation))
        newsTitle.startAnimation(AnimationUtils.loadAnimation(this,R.anim.bottom_animation))

        Timer(TIMER_NAME, false).schedule(delay = TIMER_DELAY) {
startActivity(Intent(this@MainActivity,Dashboard::class.java))
            finish()
        }
    }
}