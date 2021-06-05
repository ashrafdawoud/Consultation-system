package com.example.lawerapp.View.HomeFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.lawerapp.R
import com.example.lawerapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    lateinit var binding:FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_search,container,false)
        val view =binding.root
        contentview(view)
        return view
    }

    fun contentview(view:View){

    }

}