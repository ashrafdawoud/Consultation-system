package com.example.lawerapp.Retrofit.Entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CalenderRetrofitEntivity(
    @SerializedName("results")
    @Expose
    var results: List<CalenderEntity>
)
class CalenderEntity(
    @SerializedName("objectId")
    @Expose
    var objectId: String,
    @SerializedName("date")
    @Expose
    var date: String,
    @SerializedName("time")
    @Expose
    var time: String,
)