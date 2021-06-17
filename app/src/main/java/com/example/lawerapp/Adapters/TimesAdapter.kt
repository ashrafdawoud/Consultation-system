package com.example.lawerapp.Adapters

import android.graphics.Color
import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lawerapp.Model.CalenderModel
import com.example.lawerapp.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class TimesAdapter(val timelist: ArrayList<String>?, val onClichListeners: OnClichListeners) :
    RecyclerView.Adapter<TimesAdapter.ViewHolder>() {
    var positionclicked = -1
    var times: List<String> = mutableListOf(
        "09:00",
        "10:00",
        "11:00",
        "12:00",
        "13:05",
        "14:00",
        "15:50",
        "16:00",
        "17:00",
        "18:00"
    )

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val time = itemview.findViewById<View>(R.id.time) as TextView
        val ampm = itemview.findViewById<View>(R.id.ampm) as TextView
        val container = itemview.findViewById<View>(R.id.container) as LinearLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimesAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.getContext())
                .inflate(R.layout.times_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return times.size
    }

    override fun onBindViewHolder(holder: TimesAdapter.ViewHolder, position: Int) {
        if (positionclicked == position) {
            holder.container.background.setColorFilter(
                Color.parseColor("#172688"),
                PorterDuff.Mode.SRC_ATOP
            )
            holder.time.setTextColor(Color.WHITE)
            holder.ampm.setTextColor(Color.WHITE)
        } else {
            if (timelist != null && timelist.contains(times.get(position))) {
                holder.container.background.setColorFilter(
                    Color.parseColor("#BEBFBF"),
                    PorterDuff.Mode.SRC_ATOP
                )
                holder.time.setTextColor(Color.parseColor("#494848"))
                holder.ampm.setTextColor(Color.parseColor("#494848"))
            } else {
                holder.container.background.setColorFilter(
                    Color.parseColor("#FFAFAF"),
                    PorterDuff.Mode.SRC_ATOP
                )
                holder.time.setTextColor(Color.BLACK)
                holder.ampm.setTextColor(Color.BLACK)
            }
        }
        holder.itemView.setOnClickListener {
            if (timelist != null && timelist.contains(times.get(position))) {
            } else {
                holder.container.background.setColorFilter(
                    Color.parseColor("#172688"),
                    PorterDuff.Mode.SRC_ATOP
                )
                holder.time.setTextColor(Color.WHITE)
                holder.ampm.setTextColor(Color.WHITE)
                positionclicked = position
                onClichListeners.onclick(times.get(position))
                notifyDataSetChanged()
        }
    }

    val f1: DateFormat = SimpleDateFormat("HH:mm") //HH for hour of the day (0 - 23)
    val d: Date = f1.parse(times.get(position))
    val f2: DateFormat = SimpleDateFormat("HH:mm")
    f2.format(d).toLowerCase()

    holder.time.setText(times.get(position))
    if (f2.calendar[Calendar.HOUR_OF_DAY] >= 12)
    holder.ampm.setText("PM")
    else
    holder.ampm.setText("AM")

}

interface OnClichListeners {
    fun onclick(time: String) {
    }
}
}