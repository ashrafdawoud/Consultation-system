package com.example.lawerapp.Retrofit.Entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GovernmentRetrofitEntity (
    @SerializedName("results")
    @Expose
    var results: List<GovernmentEntity>
)
class GovernmentEntity(
    @SerializedName("objectId")
    @Expose
    var objectId: String,
    @SerializedName("government")
    @Expose
    var government: String,
    @SerializedName("Cites")
    @Expose
    var Cites: List<String>,
)