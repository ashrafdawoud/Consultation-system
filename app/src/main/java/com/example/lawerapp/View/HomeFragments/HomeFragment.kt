package com.example.lawerapp.View.HomeFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lawerapp.Adapters.CategoryAdapter
import com.example.lawerapp.Adapters.CriminalAdapter
import com.example.lawerapp.Adapters.PopularAdapter
import com.example.lawerapp.Model.CategoryModel
import com.example.lawerapp.Model.LayersModel
import com.example.lawerapp.R
import com.example.lawerapp.Utils.DataState
import com.example.lawerapp.ViewModels.CategoryViewModel
import com.example.lawerapp.ViewModels.PopularLawyersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val categoryViewModel: CategoryViewModel by viewModels()
    private val popularLawyersViewModel: PopularLawyersViewModel by viewModels()
    lateinit var categoryRecy: RecyclerView
    lateinit var pobularRecy: RecyclerView
    lateinit var criminalrecy: RecyclerView
    lateinit var categoryAdapter: CategoryAdapter
    lateinit var popularAdapter: PopularAdapter
    lateinit var crimialadapter: CriminalAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_home, container, false)
        Contentview(view)
        return view
    }

    fun Contentview(view: View) {
        categoryRecy = view.findViewById(R.id.categoryRecy)
        var linearLayoutManager: LinearLayoutManager =
            LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.HORIZONTAL, true)
        linearLayoutManager.reverseLayout = true
        linearLayoutManager.setStackFromEnd(true)
        categoryRecy.layoutManager = linearLayoutManager
        categoryRecy.hasFixedSize()
        //////////////////////////////////////////////
        pobularRecy = view.findViewById(R.id.pobularRecy)
        var linearLayoutManager2: LinearLayoutManager =
            LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.HORIZONTAL, true)
        linearLayoutManager2.reverseLayout = true
        linearLayoutManager2.setStackFromEnd(true)
        pobularRecy.layoutManager = linearLayoutManager2
        pobularRecy.hasFixedSize()
        //////////////////////////////////////////////
        criminalrecy = view.findViewById(R.id.crimimalRecy)
        var linearLayoutManager3: LinearLayoutManager =
            LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.HORIZONTAL, true)
        linearLayoutManager3.reverseLayout = true
        linearLayoutManager3.setStackFromEnd(true)
        criminalrecy.layoutManager = linearLayoutManager3
        criminalrecy.hasFixedSize()
        ////////////////////////////////////////
        categoryViewModel.getCategoryFromInternet()
        getObservalCategoryDate()
        popularLawyersViewModel.getpopularLawyers()
        getObservalPopularData()
        popularLawyersViewModel.getCriminalLawyers()
        getObservalCriminalData()
    }

    fun getObservalCategoryDate() {
        categoryViewModel.dataset.observe(viewLifecycleOwner, Observer { dataset ->
            when (dataset) {
                is DataState.Success<List<CategoryModel>> -> {
                    Log.e("HomeFragment", " succsess " + dataset.data.size)
                    categoryAdapter = CategoryAdapter(this.requireActivity(),1, dataset.data)
                    categoryRecy.adapter = categoryAdapter
                }
                is DataState.Error -> {
                    Toast.makeText(
                        activity,
                        dataset.exception.message.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                    Log.e("HomeFragment", " error " + dataset.exception.message)
                }
                is DataState.Loading -> {
                }
            }
        })
    }

    fun getObservalPopularData() {
        popularLawyersViewModel.dataset.observe(viewLifecycleOwner, Observer { dataset ->
            when (dataset) {
                is DataState.Success<List<LayersModel>> -> {
                    Log.e("HomeFragment", " succsess " + dataset.data.size)
                    popularAdapter = PopularAdapter(this.requireActivity(),dataset.data)
                    pobularRecy!!.adapter = popularAdapter
                }
                is DataState.Error -> {
                    Toast.makeText(
                        activity,
                        dataset.exception.message.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                    Log.e("HomeFragment", " error " + dataset.exception.message)
                }
                is DataState.Loading -> {
                }
            }
        })
    }

    fun getObservalCriminalData() {
        popularLawyersViewModel.datasetcriminal.observe(viewLifecycleOwner, Observer { dataset->
            when (dataset) {
                is DataState.Success<List<LayersModel>> -> {
                    Log.e("HomeFragment", " succsess " + dataset.data.size)
                    crimialadapter = CriminalAdapter(this.requireActivity(),dataset.data)
                    criminalrecy.adapter = crimialadapter
                }
                is DataState.Error -> {
                    Toast.makeText(
                        activity,
                        dataset.exception.message.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                    Log.e("HomeFragment", " error " + dataset.exception.message)
                }
                is DataState.Loading -> {
                }
            }
        })
    }

}