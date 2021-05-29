package com.example.lawerapp.View

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.lawerapp.Model.LayersModel
import com.example.lawerapp.R
import com.example.lawerapp.Utils.ActivityDesign
import com.example.lawerapp.databinding.ActivityLawyerProfileBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LawyerProfileActivity : AppCompatActivity() {
    lateinit var binding:ActivityLawyerProfileBinding
    lateinit var user : LayersModel
    @Inject
    lateinit var activityDesign:ActivityDesign
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lawyer_profile)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val i = intent
        val bundle = i.extras
        user = (bundle!!.getSerializable("user") as LayersModel?)!!
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lawyer_profile)
        contentview()
    }
    fun contentview(){
        activityDesign.excuteDesign(this)
        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.setTitle("")
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)

        binding.name.setText(user.name)
        binding.address.setText(user.address)
        binding.exp.setText(user.exp +" سنوات خبره ")
        binding.description.setText(user.discreiption)
        binding.cortAddress.setText(user.cort_location)
        binding.language.setText(user.language)
        Picasso.get().load(user.image).into(binding.profile)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }
}