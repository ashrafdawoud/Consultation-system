package com.example.lawerapp.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lawerapp.Adapters.CategoryLawyersAdapter
import com.example.lawerapp.Adapters.FavouriteAdapter
import com.example.lawerapp.Model.LayersModel
import com.example.lawerapp.Model.UserModel
import com.example.lawerapp.R
import com.example.lawerapp.Utils.ActivityDesign
import com.example.lawerapp.Utils.DataState
import com.example.lawerapp.ViewModels.CategoryLawersViewModel
import com.example.lawerapp.ViewModels.UserViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CategoryLawyersActivity : AppCompatActivity() {
    @Inject
    lateinit var activityDesign: ActivityDesign
    private val viewModel: CategoryLawersViewModel by viewModels()
    lateinit var recyclerView: RecyclerView
    lateinit var categoryLawyersAdapter: CategoryLawyersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_lawyers)
        contentview()
    }

    fun contentview() {
        val bundel = intent.extras
        val id = bundel?.get("objectId")
        toolbarThings()
        recyclerview()
        viewModel.getCategoryLawyers(id as String)
        subscribeObservers()
    }

    fun toolbarThings() {
        activityDesign.excuteDesign(this)
        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.setTitle("")
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
    }

    fun recyclerview() {
        recyclerView = findViewById(R.id.favRecy)
        var layoutManager: LinearLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )
        recyclerView.layoutManager = layoutManager
        recyclerView.hasFixedSize()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun subscribeObservers() {
        viewModel.dataset.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<LayersModel>> -> {
                    categoryLawyersAdapter = CategoryLawyersAdapter(this, dataState.data)
                    recyclerView.adapter = categoryLawyersAdapter

                }
                is DataState.Error -> {
                    Log.e("Mainactiivty", "Error " + dataState.exception.message)
                }
                is DataState.Loading -> {
                }
            }
        })
    }
}