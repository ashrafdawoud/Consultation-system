package com.example.lawerapp.View.HomeFragments

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lawerapp.Adapters.FavouriteAdapter
import com.example.lawerapp.Model.LayersModel
import com.example.lawerapp.R
import com.example.lawerapp.Utils.DataState
import com.example.lawerapp.ViewModels.FavouriteViewModel
import com.example.lawerapp.ViewModels.PopularLawyersViewModel
import com.example.lawerapp.ViewModels.UserViewModel
import com.example.lawerapp.databinding.FragmentFavouriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : Fragment(), FavouriteAdapter.Onnfavclick {
    lateinit var favRecy: RecyclerView
    lateinit var favouriteAdapter: FavouriteAdapter
    private val viewmodel: FavouriteViewModel by viewModels()
    lateinit var binding: FragmentFavouriteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourite, container, false);

        var view = binding.root
        contentview(view)
        return view
    }

    fun contentview(view: View) {
        viewmodel.getAllUsers()
        observedata()

        recyclerview(view)

    }

    override fun onResume() {
        super.onResume()
        viewmodel.getAllUsers()
    }

    fun observedata() {
        viewmodel.datasetAllLayers.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataState.Success<List<LayersModel>> -> {
                    Log.e("Mainactiivty", "sucsses " + it.data)
                    if (it.data.size != 0) {
                        favouriteAdapter = FavouriteAdapter(it.data, this)
                        favRecy.adapter = favouriteAdapter
                        binding.noitem.visibility=View.GONE
                        binding.favRecy.visibility=View.VISIBLE
                    } else {
                        binding.noitem.visibility=View.VISIBLE
                        binding.favRecy.visibility=View.GONE
                    }
                }
                is DataState.Error -> {
                    Log.e("Mainactiivty", "Error " + it.exception.message)
                    Toast.makeText(activity, "Error " + it.exception.message, Toast.LENGTH_LONG)
                        .show()
                }
                is DataState.Loading -> {

                }
            }

        })
    }

    fun recyclerview(view: View) {
        favRecy = view.findViewById(R.id.favRecy)
        var layoutManager: LinearLayoutManager =
            LinearLayoutManager(context?.applicationContext, LinearLayoutManager.VERTICAL, false)
        favRecy.layoutManager = layoutManager
        favRecy.hasFixedSize()

    }

    override fun onfaveclick(layersModel: LayersModel) {
        viewmodel.deleteuser(layersModel)
        viewmodel.datasetDelete.observe(this, Observer {
            when (it) {
                is DataState.Success<Int> -> {
                    viewmodel.getAllUsers()
                    Toast.makeText(activity, "deleted", Toast.LENGTH_LONG).show()
                }
                is DataState.Error -> {
                    Log.e("Mainactiivty", "Error " + it.exception.message)
                    Toast.makeText(activity, "Error " + it.exception.message, Toast.LENGTH_LONG)
                        .show()
                }
                is DataState.Loading -> {

                }
            }

        })

    }
}