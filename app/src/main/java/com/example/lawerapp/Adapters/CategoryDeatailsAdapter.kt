package com.example.lawerapp.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lawerapp.Model.CategoryModel
import com.example.lawerapp.R
import com.example.lawerapp.View.CategoryLawyersActivity

class CategoryDeatailsAdapter (val categoryModelList:List<CategoryModel>,val context: Context): RecyclerView.Adapter<CategoryDeatailsAdapter.ViewHolder>() {
    class ViewHolder (itemview:View) :RecyclerView.ViewHolder(itemview){
        val title:TextView=itemview.findViewById(R.id.title)
        val discription:TextView=itemview.findViewById(R.id.discription)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.getContext()).inflate(R.layout.category_details_item, parent, false)
        );

    }

    override fun getItemCount(): Int {
        return categoryModelList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.setText(categoryModelList.get(position).name)
        holder.discription.setText(categoryModelList.get(position).informations)
        holder.itemView.setOnClickListener { v->
            val intent= Intent(context, CategoryLawyersActivity::class.java)
            intent.putExtra("objectId",categoryModelList.get(position).objectId.toString())
            context.startActivity(intent)
        }
    }
}