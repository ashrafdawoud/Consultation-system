package com.example.lawerapp.Repository

import com.example.lawerapp.Model.UserModel
import com.example.lawerapp.Retrofit.Mappers.UserMaper
import com.example.lawerapp.Retrofit.RetrofitInterface
import com.example.lawerapp.Utils.DataState
import kotlinx.coroutines.flow.flow
import java.lang.Exception


class UserRepository constructor(
    private  val retrofitInterface: RetrofitInterface,
    private val userMaper: UserMaper
) {
    suspend fun getBlogs(): kotlinx.coroutines.flow.Flow<DataState<List<UserModel>>> = flow {
        emit(DataState.Loading)
        try{
            val networkUser = retrofitInterface.loginfunction("{\"email\":\"ashraf@gmail.com\",\"password\":\"123456\"}")
            val blogs = userMaper.mapFromEntityList(networkUser)
            emit(DataState.Success(blogs))
        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }
}