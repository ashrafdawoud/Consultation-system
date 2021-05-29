package com.example.lawerapp.View.HomeFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lawerapp.Adapters.CategoryAdapter
import com.example.lawerapp.Model.CategoryModel
import com.example.lawerapp.R
import com.example.lawerapp.Utils.DataState
import com.example.lawerapp.ViewModels.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {
    private val categoryViewModel: CategoryViewModel by viewModels()
    lateinit var categoryRecy:RecyclerView
    lateinit var categoryAdapter:CategoryAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_category, container, false)
        contentview(view)
        return view
    }
    fun  contentview(view:View){
        categoryRecy=view.findViewById(R.id.catRecy)
        var linearLayoutManager: LinearLayoutManager =
            GridLayoutManager(activity?.applicationContext, 2)
        categoryRecy.layoutManager=linearLayoutManager
        categoryRecy.hasFixedSize()
        categoryViewModel.getCategoryFromInternet()
        getObservalDate()
    }
    fun getObservalDate(){
        categoryViewModel.dataset.observe(viewLifecycleOwner, Observer { dataset->
            when(dataset){
                is DataState.Success<List<CategoryModel>> -> {
                    Log.e("HomeFragment"," succsess "+dataset.data.size  )
                    categoryAdapter= CategoryAdapter(this.requireActivity(),2,dataset.data)
                    categoryRecy.adapter=categoryAdapter
                }
                is DataState.Error -> {
                    Toast.makeText(activity,dataset.exception.message.toString(), Toast.LENGTH_LONG).show()
                    Log.e("HomeFragment"," error "+dataset.exception.message  )
                }
                is DataState.Loading -> {}
            }
        })
    }
}