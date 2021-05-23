package com.example.lawerapp.View

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.example.lawerapp.R

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        activityDesign()
    }
     fun contentview(){

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

    fun signup(view: View) {
        startActivity(Intent(this,SignUpActivity::class.java))
    }

    fun signin(view: View) {
        startActivity(Intent(this,HomeActivity::class.java))
    }
}