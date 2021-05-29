package com.example.lawerapp.View.HomeFragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lawerapp.Adapters.FavouriteAdapter
import com.example.lawerapp.R

class FavouriteFragment : Fragment() {
    lateinit var favRecy:RecyclerView
    lateinit var favouriteAdapter: FavouriteAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view=inflater.inflate(R.layout.fragment_favourite, container, false)
        contentview(view)
        return view
    }
    fun contentview(view:View){
        recyclerview(view)

    }
    fun recyclerview(view:View){
        favRecy=view.findViewById(R.id.favRecy)
        favouriteAdapter= FavouriteAdapter()
        var layoutManager : LinearLayoutManager= LinearLayoutManager(context?.applicationContext,LinearLayoutManager.VERTICAL,true)
        favRecy.layoutManager=layoutManager
        favRecy.hasFixedSize()
        favRecy.adapter=favouriteAdapter
    }
}