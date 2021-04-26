package com.example.lawerapp.View.HomeFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lawerapp.Adapters.CategoryAdapter
import com.example.lawerapp.Adapters.CriminalAdapter
import com.example.lawerapp.Adapters.PopularAdapter
import com.example.lawerapp.R


class HomeFragment : Fragment() {
    lateinit var categoryRecy:RecyclerView
    lateinit var pobularRecy:RecyclerView
    lateinit var criminalrecy:RecyclerView
    lateinit var categoryAdapter: CategoryAdapter
    lateinit var popularAdapter: PopularAdapter
    lateinit var crimialadapter: CriminalAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view=inflater.inflate(R.layout.fragment_home, container, false)
        Contentview(view)
        return  view
    }
    fun Contentview(view:View){
        categoryRecy=view.findViewById(R.id.categoryRecy)
        var linearLayoutManager:LinearLayoutManager=LinearLayoutManager(activity?.applicationContext,LinearLayoutManager.HORIZONTAL,true)
        linearLayoutManager.reverseLayout=true
        linearLayoutManager.setStackFromEnd(true)
        categoryRecy.layoutManager=linearLayoutManager
        categoryAdapter= CategoryAdapter()
        categoryRecy.adapter=categoryAdapter
        categoryRecy.hasFixedSize()
        //////////////////////////////////////////////
        pobularRecy=view.findViewById(R.id.pobularRecy)
        var linearLayoutManager2:LinearLayoutManager=LinearLayoutManager(activity?.applicationContext,LinearLayoutManager.HORIZONTAL,true)
        linearLayoutManager2.reverseLayout=true
        linearLayoutManager2.setStackFromEnd(true)
        pobularRecy.layoutManager=linearLayoutManager2
        popularAdapter= PopularAdapter()
        pobularRecy.adapter=popularAdapter
        pobularRecy.hasFixedSize()
        //////////////////////////////////////////////
        criminalrecy=view.findViewById(R.id.crimimalRecy)
        var linearLayoutManager3:LinearLayoutManager=LinearLayoutManager(activity?.applicationContext,LinearLayoutManager.HORIZONTAL,true)
        linearLayoutManager3.reverseLayout=true
        linearLayoutManager3.setStackFromEnd(true)
        criminalrecy.layoutManager=linearLayoutManager3
        crimialadapter= CriminalAdapter()
        criminalrecy.adapter=crimialadapter
        criminalrecy.hasFixedSize()
    }
}