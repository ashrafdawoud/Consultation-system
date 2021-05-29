package com.example.lawerapp.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lawerapp.Model.CategoryModel
import com.example.lawerapp.R
import com.example.lawerapp.View.CategoryLawyersActivity
import com.squareup.picasso.Picasso

class CategoryAdapter (val context:Context,val fromwherwActivity:Int , val data :List<CategoryModel>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (fromwherwActivity==1)
        return ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_category_item, parent, false));
        else return ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.secont_category_item, parent, false));

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener { v->
            val intent=Intent(context,CategoryLawyersActivity::class.java)
            intent.putExtra("objectId",data.get(position).objectId.toString())
            context.startActivity(intent)
        }
        Picasso.get().load(data.get(position).image.toString()).into(holder.image)
        holder.title.setText(data.get(position).name.toString())
    }

    override fun getItemCount(): Int {
        return data.size
    }
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image :ImageView=itemView.findViewById(R.id.imageid)
        var title :TextView=itemView.findViewById(R.id.titleid)

    }
}