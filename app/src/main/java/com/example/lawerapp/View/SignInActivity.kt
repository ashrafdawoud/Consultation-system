package com.example.lawerapp.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.lawerapp.Model.UserModel
import com.example.lawerapp.R
import com.example.lawerapp.Utils.ActivityDesign
import com.example.lawerapp.Utils.DataState
import com.example.lawerapp.Utils.MainStateEvent
import com.example.lawerapp.ViewModels.UserViewModel
import com.example.lawerapp.databinding.ActivitySigninBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {
    lateinit var binding: ActivitySigninBinding

    @Inject
    lateinit var activityDesign: ActivityDesign
    private val viewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signin)
        contentview()
    }

    fun contentview() {
        activityDesign.excuteDesign(this)

    }


    fun signup(view: View) {

        startActivity(Intent(this, SignUpActivity::class.java))
    }

    fun signin(view: View) {
        if (binding.email.text.toString().equals("")) {
            binding.email.setError("this field can't be empety")
        } else if (binding.password.text.toString().equals("")) {
            binding.password.setError("this field can't be empety")
        } else {
            viewModel.setStateEvent(
                MainStateEvent.GetBlogsEvent,
                binding.email.text.toString(),
                binding.password.text.toString()
            )
            subscribeObservers()

        }


    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<UserModel> -> {
                    Log.e("Mainactiivty", "sucsses " + dataState.data)
                    if (binding.email.text.toString().equals(dataState.data.email)&&binding.password.text.toString().equals(dataState.data.password)){
                        startActivity(Intent(this, HomeActivity::class.java))
                    }else{
                        Toast.makeText(this,"try again with correct email or password",Toast.LENGTH_LONG).show()
                    }
                }
                is DataState.Error -> {
                    Log.e("Mainactiivty", "Error "+dataState.exception.message)
                    Toast.makeText(this,"Error "+dataState.exception.message,Toast.LENGTH_LONG).show()
                }
                is DataState.Loading -> {
                }
            }
        })
    }
}