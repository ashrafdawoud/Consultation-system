package com.example.lawerapp.Retrofit

import com.example.lawerapp.Model.UserModel
import com.example.lawerapp.Retrofit.Entities.*
import org.w3c.dom.Entity
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetrofitInterface {
    @Headers(
        "X-Parse-Application-Id:m4fV65YIwkezflLzC5dVe8t1XMlWyV1FUsZ2IVkw",
        "X-Parse-REST-API-Key:0SISQ04mVSUHOEGL6q5VwkE9UuS3OFySUkZ0BL8L"
    )
    @GET("User1")
    suspend fun loginfunction(@Query("where") data:String):UserRetrofitEntity
    @Headers(
        "X-Parse-Application-Id:m4fV65YIwkezflLzC5dVe8t1XMlWyV1FUsZ2IVkw",
        "X-Parse-REST-API-Key:0SISQ04mVSUHOEGL6q5VwkE9UuS3OFySUkZ0BL8L",
        "Accept: application/json"
    )
    @FormUrlEncoded
    @POST("User1")
    suspend fun signupfunction(@FieldMap  options:Map<String, String>) : SucssesEntity
    @Headers(
        "X-Parse-Application-Id:m4fV65YIwkezflLzC5dVe8t1XMlWyV1FUsZ2IVkw",
        "X-Parse-REST-API-Key:0SISQ04mVSUHOEGL6q5VwkE9UuS3OFySUkZ0BL8L"
    )
    @GET("Category")
    suspend fun getCategory() :CategoryRetrofitEntity
    @Headers(
        "X-Parse-Application-Id:m4fV65YIwkezflLzC5dVe8t1XMlWyV1FUsZ2IVkw",
        "X-Parse-REST-API-Key:0SISQ04mVSUHOEGL6q5VwkE9UuS3OFySUkZ0BL8L"
    )
    @GET("Lawyers?where={\"popular\" : true}")
    suspend fun getpopularLawers() :PopularLawersRetrofitEntity
    @Headers(
        "X-Parse-Application-Id:m4fV65YIwkezflLzC5dVe8t1XMlWyV1FUsZ2IVkw",
        "X-Parse-REST-API-Key:0SISQ04mVSUHOEGL6q5VwkE9UuS3OFySUkZ0BL8L"
    )
    @GET("Lawyers?where={\"category\" : \"fuwMtotDER\"}")
    suspend fun getCrimanalLawers() :PopularLawersRetrofitEntity
    @Headers(
        "X-Parse-Application-Id:m4fV65YIwkezflLzC5dVe8t1XMlWyV1FUsZ2IVkw",
        "X-Parse-REST-API-Key:0SISQ04mVSUHOEGL6q5VwkE9UuS3OFySUkZ0BL8L"
    )
    @GET("Lawyers")
    suspend fun getCategoryLawers(@Query("where") data:String) :PopularLawersRetrofitEntity
}