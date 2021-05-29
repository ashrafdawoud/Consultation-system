package com.example.lawerapp.Retrofit.Entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CategoryRetrofitEntity (
    @SerializedName("results")
    @Expose
    var results: List<CategoryEntity>
)
class CategoryEntity(
    @SerializedName("objectId")
    @Expose
    var objectId: String,
    @SerializedName("Name")
    @Expose
    var Name: String,
    @SerializedName("image")
    @Expose
    var image: String,

)