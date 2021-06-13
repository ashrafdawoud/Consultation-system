package com.example.lawerapp.View

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.example.lawerapp.R
import com.example.lawerapp.Utils.ActivityDesign
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class ReversationActivity : AppCompatActivity() {
    lateinit var times_recy:RecyclerView
    @Inject
     lateinit var activityDesign:ActivityDesign
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reversation)
        contentview()
    }
    fun contentview(){
        val i = intent
        val bundle = i.extras
        val object_id:String=bundle!!.getString("object_id","")
        activityDesign.excuteDesign(this)
        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.setTitle("")
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        setup_calender()
        setup_recyclerview()
    }
    fun  setup_calender(){
        val calendar: Calendar = Calendar.getInstance()
        val calendarView: CalendarView = findViewById<View>(R.id.calendarView) as CalendarView
        val c = Calendar.getInstance()
        val day = c[Calendar.DAY_OF_MONTH]
        val month = c[Calendar.MONTH]
        val year = c[Calendar.YEAR]
        val date = day.toString() + "/" + (month + 1) + "/" + year
        Log.e("dateandtime",date)
        calendar.set(year,month,day)
        calendarView.setDate(calendar);
        val events: MutableList<EventDay> = ArrayList()
        events.add(EventDay(calendar, R.drawable.ic_profile_new, Color.RED))
        calendarView.setEvents(events)
        calendarView.setOnDayClickListener { eventDay ->
            val clickedDayCalendar = eventDay.calendar
            Log.e("dateandtime1",clickedDayCalendar[Calendar.DAY_OF_MONTH].toString())
            calendar.set(clickedDayCalendar[Calendar.YEAR],clickedDayCalendar[Calendar.MONTH],clickedDayCalendar[Calendar.DAY_OF_MONTH])
            calendarView.setDate(calendar);
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }
    fun setup_recyclerview(){
        times_recy=findViewById(R.id.timesRecy)
        var layoutManager: LinearLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL, false
        )
        times_recy.layoutManager = layoutManager
        times_recy.hasFixedSize()
    }
}