package com.example.lawerapp.Repository

import android.util.Log
import com.example.lawerapp.Model.UserModel
import com.example.lawerapp.Retrofit.Entities.UserRetrofitEntity
import com.example.lawerapp.Retrofit.Mappers.UserMaper
import com.example.lawerapp.Retrofit.RetrofitInterface
import com.example.lawerapp.Utils.DataState
import kotlinx.coroutines.flow.flow
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit


class UserRepository constructor(
    private val retrofitInterface: RetrofitInterface,
    private val userMaper: UserMaper
) {
    suspend fun getBlogs(email:String,password:String): kotlinx.coroutines.flow.Flow<DataState<UserModel>> = flow {
        emit(DataState.Loading)
        try {
            val networkUser =
                retrofitInterface.loginfunction("{\"email\":\""+email+"\",\"password\":\""+password+"\"}")
                //retrofitInterface.loginfunction("{\"email\":\"ashraf@gmail.com\",\"password\":\"123456\"}")
            //val blogs = userMaper.mapFromEntityList(networkUser)
            val blogs = userMaper.mapFromEntity(networkUser)
            emit(DataState.Success(blogs))
            Log.e("userrepo", "sucseess " + networkUser.results.get(0).email)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            Log.e("userrepo", "Error " + e +"{email:"+email+",password:"+password+"}")

        }
    }
    suspend fun postuser(map : HashMap<String,String>){
        val res=retrofitInterface.signupfunction(map)
        try {
            Log.e("userrepo", "respond " + res.objectId )
        }catch (e:java.lang.Exception){}
    }
}