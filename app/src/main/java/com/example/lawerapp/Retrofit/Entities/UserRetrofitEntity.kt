package com.example.lawerapp.Retrofit.Entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

 class UserRetrofitEntity (
    @SerializedName("objectId")
    @Expose
    var objectId:String,
    @SerializedName("first_name")
    @Expose
    var first_name:String,
    @SerializedName("second_name")
    @Expose
    var second_name:String,
    @SerializedName("email")
    @Expose
    var email:String,
    @SerializedName("phone")
    @Expose
    var phone:String,
    @SerializedName("password")
    @Expose
    var password:String,
        )