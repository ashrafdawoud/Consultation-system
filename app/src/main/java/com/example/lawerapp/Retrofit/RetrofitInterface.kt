package com.example.lawerapp.Retrofit

import com.example.lawerapp.Retrofit.Entities.UserRetrofitEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("User")
    suspend fun loginfunction(@Query("where") data:String):List<UserRetrofitEntity>
}