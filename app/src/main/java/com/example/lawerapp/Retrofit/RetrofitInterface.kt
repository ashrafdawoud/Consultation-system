package com.example.lawerapp.Retrofit

import com.example.lawerapp.Retrofit.Entities.UserRetrofitEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitInterface {
    @Headers(
        "X-Parse-Application-Id:m4fV65YIwkezflLzC5dVe8t1XMlWyV1FUsZ2IVkw",
        "X-Parse-REST-API-Key:0SISQ04mVSUHOEGL6q5VwkE9UuS3OFySUkZ0BL8L"
    )
    @GET("User")
    suspend fun loginfunction():UserRetrofitEntity//@Query("where") data:String):UserRetrofitEntity
}