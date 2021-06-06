package com.example.lawerapp.View.HomeFragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.lawerapp.Model.CategoryModel
import com.example.lawerapp.Model.GovernmentModel
import com.example.lawerapp.R
import com.example.lawerapp.Utils.DataState
import com.example.lawerapp.View.SearchResultActivity
import com.example.lawerapp.ViewModels.CategoryViewModel
import com.example.lawerapp.ViewModels.SearchViewModel
import com.example.lawerapp.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    val category_viewmodel: CategoryViewModel by viewModels()
    val government_viewmodel: SearchViewModel by viewModels()
    lateinit var catSpinner: Spinner
    lateinit var govSpinner: Spinner
    lateinit var citySpinner: Spinner
    lateinit var govmodel:List<GovernmentModel>
    lateinit var government:String
    lateinit var city:String
    lateinit var category:String
    lateinit var search:CardView
    lateinit var layername:EditText
    lateinit var categoryid:List<CategoryModel>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        val view = binding.root
        contentview(view)
        return view
    }

    fun contentview(view: View) {
        spinners_setup(view)
        call_viewmodels()
        search=view.findViewById(R.id.search)
        layername=view.findViewById(R.id.layername)
        search.setOnClickListener {
            Log.e("namename12",layername.text.toString())
            val intent=Intent(activity,SearchResultActivity::class.java)
            intent.putExtra("city",city)
            intent.putExtra("category",category)
            intent.putExtra("government",government)
            intent.putExtra("layername",layername.text.toString())
            activity?.startActivity(intent)
        }

    }

    fun call_viewmodels() {
        category_viewmodel.getCategoryFromInternet()
        government_viewmodel.getgovernemts()
        observe_viewmodels()
    }
    fun observe_viewmodels() {
        category_viewmodel.dataset.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataState.Success<List<CategoryModel>> -> {
                    val categoryList=it.data.map { it.name }
                    categoryid=it.data
                    val aa = ArrayAdapter<String>(this.requireActivity(), android.R.layout.simple_spinner_item, categoryList)
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    catSpinner.adapter = aa


                }
                is DataState.Error -> {

                }
                is DataState.Loading -> {

                }
            }
        })
        government_viewmodel.dataset.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataState.Success<List<GovernmentModel>> -> {
                    govmodel=it.data
                    val govList=it.data.map { it.goverment }
                    val aa = ArrayAdapter<String>(this.requireActivity(), android.R.layout.simple_spinner_item, govList)
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    govSpinner.adapter = aa
                   /* val aacity = ArrayAdapter<String>(this.requireActivity(), android.R.layout.simple_spinner_item, it.data.get(0).cites)
                    aacity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    citySpinner.adapter=aacity*/

                }
                is DataState.Error -> {

                }
                is DataState.Loading -> {

                }
            }
        })
    }

    fun spinners_setup(view: View) {
        catSpinner = view.findViewById(R.id.category) as Spinner
        govSpinner = view.findViewById(R.id.government) as Spinner
        citySpinner = view.findViewById(R.id.city) as Spinner
        catSpinner.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View, position: Int, id: Long) {
               category=categoryid.get(position).objectId
            }
            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        })
        govSpinner.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View, position: Int, id: Long) {
                government=parentView?.getItemAtPosition(position).toString()
                val aacity = activity?.let { ArrayAdapter<String>(it, android.R.layout.simple_spinner_item, govmodel.get(position).cites) }
                aacity?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                citySpinner.adapter=aacity
            }
            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        })
        citySpinner.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View, position: Int, id: Long) {
                city=parentView?.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        })


    }

}