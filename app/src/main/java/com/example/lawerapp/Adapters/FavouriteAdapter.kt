package com.example.lawerapp.Adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lawerapp.Model.LayersModel
import com.example.lawerapp.R
import com.example.lawerapp.View.LawyerProfileActivity
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class FavouriteAdapter(val layers: List<LayersModel>, val onfavclick: Onnfavclick) :
    RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {
    lateinit var context: Context

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: CircleImageView = itemView.findViewById(R.id.profile_image)
        val fav: ImageView = itemView.findViewById(R.id.favourite)
        val name: TextView = itemView.findViewById(R.id.name)
        val price: TextView = itemView.findViewById(R.id.price)
        val exp: TextView = itemView.findViewById(R.id.exp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_item, parent, false)
        );
    }

    override fun getItemCount(): Int {
        return layers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(layers.get(position).image).into(holder.image)
        holder.name.setText(layers.get(position).name)
        holder.price.setText("${layers.get(position).price} $/hr")
        holder.exp.setText("${layers.get(position).exp} years experience ")
        holder.itemView.setOnClickListener {
            val intent = Intent(context, LawyerProfileActivity::class.java)
            val b = Bundle()
            b.putSerializable("user", layers.get(position))
            intent.putExtras(b) //pass bundle to your intent
            context.startActivity(intent)
        }
        holder.fav.setOnClickListener {
            onfavclick.onfaveclick(layers.get(position))
        }
    }

    interface Onnfavclick {
        fun onfaveclick(layersModel: LayersModel)
    }
}