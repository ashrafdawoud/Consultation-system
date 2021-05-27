package com.example.lawerapp.View

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.lawerapp.R
import com.example.lawerapp.Utils.ActivityDesign
import com.example.lawerapp.ViewModels.UserViewModel
import com.example.lawerapp.databinding.ActivitySignUpBinding
import com.example.lawerapp.databinding.ActivitySigninBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding

    @Inject
    lateinit var activityDesign: ActivityDesign
    private val viewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        contentview()
    }

    fun contentview() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        activityDesign.excuteDesign(this)
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitle("")
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun signup2(view: View) {
        if (!binding.name.text.toString().equals("")
            && !binding.email.text.toString().equals("")
            && !binding.password.text.toString().equals("")
            && !binding.repassword.text.toString().equals("")
            && !binding.phone.text.toString().equals("")
        ) {
            if (binding.password.text.toString().equals(binding.repassword.text.toString())
            ) {
                val hashMap: HashMap<String, String> = HashMap<String, String>()
                hashMap.put("first_name", binding.name.text.toString());
                hashMap.put("second_name", binding.name.text.toString());
                hashMap.put("email", binding.email.text.toString());
                hashMap.put("phone", binding.phone.text.toString());
                hashMap.put("password", binding.password.text.toString());
                viewModel.postuser(hashMap)
            } else {
                binding.password.setError("password and repassword should be equal")
                binding.repassword.setError("password and repassword should be equal")
            }
        }else{
            binding.password.setError("this field can't be empty")
            binding.repassword.setError("this field can't be empty")
            binding.name.setError("this field can't be empty")
            binding.phone.setError("this field can't be empty")
            binding.email.setError("this field can't be empty")
        }
    }
}