package com.example.lawerapp.Retrofit.Entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PopularLawersRetrofitEntity (
    @SerializedName("results")
    @Expose
    val results:List<PopularLawersitEntity>,
)
class PopularLawersitEntity(
    @SerializedName("objectId")
    @Expose
    val objectId:String,
    @SerializedName("name")
    @Expose
    val name:String,
    @SerializedName("address")
    @Expose
    val address:String,
    @SerializedName("exp")
    @Expose
    val exp:String,
    @SerializedName("discreiption")
    @Expose
    val discreiption:String,
    @SerializedName("cort_location")
    @Expose
    val cort_location:String,
    @SerializedName("language")
    @Expose
    val language:String,
    @SerializedName("popular")
    @Expose
    val popular:Boolean,
    @SerializedName("category")
    @Expose
    val category:String,
    @SerializedName("image")
    @Expose
    val image:String,
    @SerializedName("price")
    @Expose
    val price:String,
)