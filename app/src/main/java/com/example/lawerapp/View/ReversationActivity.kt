package com.example.lawerapp.View

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.example.lawerapp.Adapters.TimesAdapter
import com.example.lawerapp.Model.CalenderModel
import com.example.lawerapp.R
import com.example.lawerapp.Retrofit.Entities.SucssesEntity
import com.example.lawerapp.Utils.ActivityDesign
import com.example.lawerapp.Utils.DataState
import com.example.lawerapp.ViewModels.CalenderViewModel
import com.example.lawerapp.ViewModels.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


@AndroidEntryPoint
class ReversationActivity : AppCompatActivity(), TimesAdapter.OnClichListeners {
     var selected_time: String =""
     var selected_date: String =""
    lateinit var times_recy: RecyclerView
    lateinit var timeadapter: TimesAdapter
    val viewmodel: CalenderViewModel by viewModels()
    lateinit var  enroll:CardView
    @Inject
    lateinit var activityDesign: ActivityDesign
    lateinit var object_id: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reversation)
        contentview()
    }

    fun contentview() {
        val i = intent
        val bundle = i.extras
        object_id = bundle!!.getString("object_id", "")
        activityDesign.excuteDesign(this)
        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.setTitle("")
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        setup_recyclerview()
        setup_calender()
    }

    fun setup_calender() {
        enroll=findViewById(R.id.enroll)
        enroll.setOnClickListener {
            if (!selected_date.equals("")||!selected_time.equals("")) {
                val hashMap: HashMap<String, String> = HashMap<String, String>()
                hashMap.put("date", selected_date);
                hashMap.put("time", selected_time);
                hashMap.put("lawyer_id", object_id);
                viewmodel.postappointent(hashMap)
            }else{
                Toast.makeText(this,"should choose time and date",Toast.LENGTH_SHORT).show()
            }

        }
        val calendar: Calendar = Calendar.getInstance()
        val calendarView: CalendarView = findViewById<View>(R.id.calendarView) as CalendarView
        val c = Calendar.getInstance()
        val day = c[Calendar.DAY_OF_MONTH]
        val month = c[Calendar.MONTH]
        val year = c[Calendar.YEAR]
        val date = day.toString() + "/" + (month + 1) + "/" + year
        selected_date=date
        viewmodel.getdata(object_id, date)
        Log.e("dateandtime", date)
        calendar.set(year, month, day)
        calendarView.setDate(calendar);
        val events: MutableList<EventDay> = ArrayList()
        events.add(EventDay(calendar, R.drawable.ic_profile_new, Color.RED))
        calendarView.setEvents(events)
        calendarView.setOnDayClickListener { eventDay ->
            val clickedDayCalendar = eventDay.calendar
            Log.e("dateandtime1", clickedDayCalendar[Calendar.DAY_OF_MONTH].toString())
            calendar.set(
                clickedDayCalendar[Calendar.YEAR],
                clickedDayCalendar[Calendar.MONTH],
                clickedDayCalendar[Calendar.DAY_OF_MONTH])
            val date = clickedDayCalendar[Calendar.DAY_OF_MONTH].toString() + "/" + (clickedDayCalendar[Calendar.MONTH] + 1) + "/" + clickedDayCalendar[Calendar.YEAR]
            selected_date=date
            viewmodel.getdata(object_id, date)
            calendarView.setDate(calendar)
        }
        observeViewModel()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }

    fun setup_recyclerview() {
        times_recy = findViewById(R.id.timesRecy)
        var layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        times_recy.layoutManager = layoutManager
        timeadapter = TimesAdapter(null, this)
        times_recy.adapter = timeadapter
        times_recy.hasFixedSize()
    }

    override fun onclick(time: String) {
        super.onclick(time)
        selected_time = time
        Log.e("onclicklistener",selected_time)
    }

    fun observeViewModel() {
        viewmodel.dataset.observe(this, androidx.lifecycle.Observer {
            when (it) {
                is DataState.Success<List<CalenderModel>> -> {
                    var dates: ArrayList<String> = ArrayList()
                    for (item in it.data) {
                        dates.add(item.time)
                    }
                    timeadapter = TimesAdapter(dates, this)
                    timeadapter.notifyDataSetChanged()
                    times_recy.adapter = timeadapter

                }
                is DataState.Error -> {
                    timeadapter = TimesAdapter(null, this)
                    times_recy.adapter = timeadapter
                }
                is DataState.Loading -> {
                }
            }
        })
        viewmodel.dataStatesignup.observe(this, androidx.lifecycle.Observer {
            when (it) {
                is DataState.Success<SucssesEntity> -> {
                    Toast.makeText(this,"Sucsess",Toast.LENGTH_LONG).show()
                    finish()
                }
                is DataState.Error -> {
                    Toast.makeText(this,it.exception.message,Toast.LENGTH_LONG).show()
                }
                is DataState.Loading ->{
                    Toast.makeText(this,"Loading",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}