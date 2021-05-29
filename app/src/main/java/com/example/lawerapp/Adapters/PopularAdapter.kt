package com.example.lawerapp.Adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lawerapp.Model.LayersModel
import com.example.lawerapp.R
import com.example.lawerapp.View.LawyerProfileActivity
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class PopularAdapter  constructor(val context: Context,val data :List<LayersModel>): RecyclerView.Adapter<PopularAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pobular_item, parent, false));
    }

    override fun onBindViewHolder(holder: PopularAdapter.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val yourIntent :Intent = Intent(context, LawyerProfileActivity::class.java)
            val b = Bundle()
            b.putSerializable("user", data.get(position))
            yourIntent.putExtras(b) //pass bundle to your intent
            context.startActivity(yourIntent)
        }
        Picasso.get().load(data.get(position).image).into(holder.image)
        holder.name.setText(data.get(position).name)
        holder.price.setText("${data.get(position).price} $/hr")
    }

    override fun getItemCount(): Int {
        return data.size
    }
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image : CircleImageView=itemView.findViewById(R.id.profile_image)
        val name : TextView=itemView.findViewById(R.id.name)
        val price : TextView=itemView.findViewById(R.id.price)
    }
}