package com.example.lawerapp.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lawerapp.R
import com.example.lawerapp.View.LawyerProfileActivity

class FavouriteAdapter () :RecyclerView.Adapter<FavouriteAdapter.ViewHolder>(){
    lateinit var  context:Context
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context =parent.context
        return ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_item, parent, false));
    }

    override fun getItemCount(): Int {
        return 10
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {  context.startActivity(Intent(context,LawyerProfileActivity::class.java))}
    }

}