package com.example.lawerapp.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.Dataset
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lawerapp.Adapters.CategoryDeatailsAdapter
import com.example.lawerapp.Model.CategoryModel
import com.example.lawerapp.R
import com.example.lawerapp.Utils.ActivityDesign
import com.example.lawerapp.Utils.DataState
import com.example.lawerapp.ViewModels.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class CategoryDetailsActivity : AppCompatActivity() {
    lateinit var categoryDeatailsAdapter: CategoryDeatailsAdapter
    val viewmodel: CategoryViewModel by viewModels()
    @Inject
    lateinit var activityDesign:ActivityDesign
    lateinit var categoriesRecy:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDesign.excuteDesign(this)
        setContentView(R.layout.activity_category_details)
        contentview()
    }

    fun contentview() {
        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.setTitle("")
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        //////////////////////////////////////////////////////
        setup_recyclerview()
        //////////////////////////////////////////////////////
        viewmodel.getCategoryFromInternet()
        listen_for_observers()
    }
    fun  setup_recyclerview(){
        categoriesRecy=findViewById(R.id.categoryRecy)
        var layoutManager: LinearLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )
        categoriesRecy.layoutManager = layoutManager
        categoriesRecy.hasFixedSize()
    }

    fun listen_for_observers() {
        viewmodel.dataset.observe(this, Observer {
            when (it) {
                is DataState.Success<List<CategoryModel>> -> {
                    Log.e("calledformdd",it.data.size.toString())
                    categoryDeatailsAdapter= CategoryDeatailsAdapter(it.data,this)
                    categoriesRecy.adapter=categoryDeatailsAdapter
                    categoryDeatailsAdapter.notifyDataSetChanged()
                }
                is DataState.Loading -> {

                }
                is DataState.Error -> {
                    Log.e("calledformdd",it.exception.message.toString())

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