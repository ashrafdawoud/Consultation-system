package com.example.lawerapp.View

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import com.example.lawerapp.Model.UserModel
import com.example.lawerapp.R
import com.example.lawerapp.Utils.ActivityDesign
import com.example.lawerapp.Utils.DataState
import com.example.lawerapp.Utils.MainStateEvent
import com.example.lawerapp.ViewModels.UserViewModel
import com.github.ybq.android.spinkit.sprite.Sprite

import com.github.ybq.android.spinkit.style.Circle
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var bool: Boolean = true
    @Inject
    lateinit var activityDesign:ActivityDesign
    private val viewModel: UserViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            contentview()
        }

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun contentview() {
        activityDesign.excuteDesign(this)
        viewModel.setStateEvent(MainStateEvent.GetBlogsEvent)
        val progressBar = findViewById<ProgressBar>(R.id.progressbar)
        val doubleBounce: Sprite = Circle()
        doubleBounce.setColor(resources.getColor(R.color.major))
        progressBar.indeterminateDrawable = doubleBounce
        lanch_next_screen()
        subscribeObservers()

    }

    fun lanch_next_screen() {
        val intent=Intent(this, SignInActivity::class.java)
        GlobalScope.launch {
            runBlocking {
                delay(3000)
                startActivity(intent)
                finish()
            }

        }
    }
    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<UserModel>> -> {
                    Log.e("Mainactiivty","sucsses ")
                }
                is DataState.Error -> {
                    Log.e("Mainactiivty","Error ")
                }
                is DataState.Loading -> {
                }
            }
        })
    }
}