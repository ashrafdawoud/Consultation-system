package com.example.lawerapp.Retrofit.Entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SucssesEntity(
    @SerializedName("objectId")
    @Expose
    val objectId: String,
    @SerializedName("createdAt")
    @Expose
    val createdAt: String
)