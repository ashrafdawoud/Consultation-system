package com.example.lawerapp.View.HomeFragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import com.example.lawerapp.R
import com.example.lawerapp.View.SignInActivity
import com.example.lawerapp.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var fragmentProfileBinding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentProfileBinding=DataBindingUtil.inflate(
            inflater, R.layout.fragment_profile, container, false);
        val view = fragmentProfileBinding.root
        contentview(view)
        return view
    }
    fun contentview(veiw:View){
        sharedPreferences =
            this.requireActivity().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE)
        fragmentProfileBinding.email.setText(sharedPreferences.getString("email"," "))
        fragmentProfileBinding.name.setText(sharedPreferences.getString("name"," "))
        fragmentProfileBinding.name1.setText(sharedPreferences.getString("name"," "))
        fragmentProfileBinding.phone.setText(sharedPreferences.getString("phone"," "))
        fragmentProfileBinding.signout.setOnClickListener { v->
            sharedPreferences.edit().clear().commit()
            this.requireActivity().finishAffinity()
            this.requireActivity().startActivity(Intent(this.requireContext(),SignInActivity::class.java))
        }
    }

}