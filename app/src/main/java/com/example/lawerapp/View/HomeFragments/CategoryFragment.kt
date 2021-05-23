package com.example.lawerapp.View.HomeFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lawerapp.Adapters.CategoryAdapter
import com.example.lawerapp.R


class CategoryFragment : Fragment() {
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
        categoryAdapter= CategoryAdapter(2)
        categoryRecy.adapter=categoryAdapter
        categoryRecy.hasFixedSize()
    }
}