package com.example.lawerapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lawerapp.R

class CriminalAdapter : RecyclerView.Adapter<CriminalAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.criminal_item, parent, false));
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 8
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}