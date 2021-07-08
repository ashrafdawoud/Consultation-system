package com.example.lawerapp.View

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.lawerapp.Model.LayersModel
import com.example.lawerapp.Model.UserModel
import com.example.lawerapp.R
import com.example.lawerapp.Utils.ActivityDesign
import com.example.lawerapp.Utils.DataState
import com.example.lawerapp.ViewModels.FavouriteViewModel
import com.example.lawerapp.ViewModels.UserViewModel
import com.example.lawerapp.databinding.ActivityLawyerProfileBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LawyerProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityLawyerProfileBinding
    lateinit var user: LayersModel
    private val viewModel: FavouriteViewModel by viewModels()
    var isfav = false

    @Inject
    lateinit var activityDesign: ActivityDesign
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lawyer_profile)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_lawyer_profile)
        contentview()
    }
    fun contentview() {
        activityDesign.excuteDesign(this)
        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.setTitle("")
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)

        binding.name.setText(user.name)
        binding.address.setText(user.address)
        binding.exp.setText(user.exp + " سنوات خبره ")
        binding.description.setText(user.discreiption)
        binding.cortAddress.setText(user.cort_location)
        binding.language.setText(user.language)
        Picasso.get().load(user.image).into(binding.profile)
        viewModel.getoneuser(user)
        getobserverdata()
        binding.fab.setOnClickListener {
            if (!isfav) {
                viewModel.insertuser(user)
                val myFabSrc = resources.getDrawable(R.drawable.ic_baseline_favorite_24)
                val willBeWhite = myFabSrc.constantState!!.newDrawable()
                willBeWhite.mutate().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY)
                binding.fab.setImageDrawable(willBeWhite)
            } else {
                viewModel.deleteuser(user)
                val myFabSrc = resources.getDrawable(R.drawable.ic_baseline_favorite_24)
                val willBeWhite = myFabSrc.constantState!!.newDrawable()
                willBeWhite.mutate().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY)
                binding.fab.setImageDrawable(willBeWhite)
            }

        }
        binding.enroll.setOnClickListener {
            val intent:Intent= Intent(this,ReversationActivity::class.java)
            intent.putExtra("object_id",user.objectId)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }

    fun getobserverdata() {
        viewModel.datasetgetone.observe(this, Observer {
            when (it) {
                is DataState.Success<Boolean> -> {
                    Log.e("Mainactiivty", "sucsses " + it.data)
                    isfav = it.data
                    if (isfav==true){
                        val myFabSrc = resources.getDrawable(R.drawable.ic_baseline_favorite_24)
                        val willBeWhite = myFabSrc.constantState!!.newDrawable()
                        willBeWhite.mutate().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY)
                        binding.fab.setImageDrawable(willBeWhite)
                    }else{
                        val myFabSrc = resources.getDrawable(R.drawable.ic_baseline_favorite_24)
                        val willBeWhite = myFabSrc.constantState!!.newDrawable()
                        willBeWhite.mutate().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY)
                        binding.fab.setImageDrawable(willBeWhite)
                    }
                }
                is DataState.Error -> {
                    Log.e("Mainactiivty", "Error " + it.exception.message)
                    Toast.makeText(this, "Error " + it.exception.message, Toast.LENGTH_LONG)
                        .show()
                }
                is DataState.Loading -> {
                }
            }
        })
        viewModel.datasetInsert.observe(this, Observer {
            when (it) {
                is DataState.Success<Long> -> {
                    Log.e("Mainactiivty", "sucsses " + it.data)
                    if (it != null) {
                        isfav=true
                        Toast.makeText(this, "Added To Favourite " + it.data, Toast.LENGTH_LONG).show()
                    }
                }
                is DataState.Error -> {
                    Log.e("Mainactiivty", "Error " + it.exception.message)
                    Toast.makeText(this, "Error " + it.exception.message, Toast.LENGTH_LONG)
                        .show()
                }
                is DataState.Loading -> {
                }
            }

        })
        viewModel.datasetDelete.observe(this, Observer {
            when (it) {
                is DataState.Success<Int> -> {
                    Log.e("Mainactiivty", "sucsses " + it.data)
                    if (it.data!=0) {
                        isfav=false
                        Toast.makeText(this, "Deleted From Favourite " + it.data, Toast.LENGTH_LONG).show()
                    }
                }
                is DataState.Error -> {
                    Log.e("Mainactiivty", "Error " + it.exception.message)
                    Toast.makeText(this, "Error " + it.exception.message, Toast.LENGTH_LONG)
                        .show()
                }
                is DataState.Loading -> {
                }
            }

        })
    }
}