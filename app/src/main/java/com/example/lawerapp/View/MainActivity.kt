package com.example.lawerapp.View

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.lawerapp.R
import com.github.ybq.android.spinkit.sprite.Sprite

import com.github.ybq.android.spinkit.style.Circle


class MainActivity : AppCompatActivity() {
    var bool:Boolean=true
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        activityDesign()
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            contentview()
        }

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun contentview(){
        val progressBar = findViewById<ProgressBar>(R.id.progressbar)
        val doubleBounce: Sprite = Circle()
        doubleBounce.setColor(resources.getColor(R.color.major))
        progressBar.indeterminateDrawable = doubleBounce
        Handler().postDelayed(Runnable {
            if (bool) {
                bool=false
                startActivity(Intent(this, SignupActivity::class.java))
                finish()
            }
        },3000)
    }
    fun activityDesign() {
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.white)
            window.navigationBarColor = resources.getColor(R.color.white)
        }
    }
}