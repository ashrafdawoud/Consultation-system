package com.example.lawerapp.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lawerapp.Adapters.CategoryLawyersAdapter
import com.example.lawerapp.Adapters.FavouriteAdapter
import com.example.lawerapp.Model.LayersModel
import com.example.lawerapp.R
import com.example.lawerapp.Utils.ActivityDesign
import com.example.lawerapp.Utils.DataState
import com.example.lawerapp.ViewModels.SearchResultViewModel
import com.example.lawerapp.databinding.ActivitySearchResultBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchResultActivity : AppCompatActivity() {
    lateinit var binding:ActivitySearchResultBinding
    lateinit var government:String
    lateinit var city:String
    lateinit var category:String
    lateinit var layername: String
    lateinit var recyclerView:RecyclerView
    lateinit var categoryLawyersAdapter:CategoryLawyersAdapter
    @Inject
    lateinit var activityDesign: ActivityDesign
    val viewmodel:SearchResultViewModel by viewModels ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_search_result)
        val bundle=intent.extras
        if (bundle!=null){
            government=bundle.getString("government","")
            city=bundle.getString("city","")
            category=bundle.getString("category","")
            layername=bundle.getString("layername","")
        }
        contentview()

    }
    fun contentview(){
        activityDesign.excuteDesign(this)
        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.setTitle("")
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        Log.e("namename11",layername)
        viewmodel.searchfunction(city,category,layername)
        recyclerview()
        observable()

    }
    fun recyclerview() {
        recyclerView = findViewById(R.id.searchRecy)
        var layoutManager: LinearLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )
        recyclerView.layoutManager = layoutManager
        recyclerView.hasFixedSize()
    }
    fun observable(){
        viewmodel.dataset.observe(this, Observer {
            when (it) {
                is DataState.Success<List<LayersModel>> -> {

                    if (it.data.size != 0) {
                        categoryLawyersAdapter = CategoryLawyersAdapter(this, it.data)
                        recyclerView.adapter = categoryLawyersAdapter
                        binding.noitem.visibility=View.GONE
                        binding.searchRecy.visibility=View.VISIBLE
                    } else {
                        binding.noitem.visibility=View.VISIBLE
                        binding.searchRecy.visibility=View.GONE
                    }
                }
                is DataState.Error -> {
                    Toast.makeText(this, "Error " + it.exception.message, Toast.LENGTH_LONG)
                        .show()
                }
                is DataState.Loading -> {

                }
            }
        })
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }
}