package com.example.lawerapp.Repository

import android.util.Log
import com.example.lawerapp.Model.CategoryModel
import com.example.lawerapp.Retrofit.Mappers.CategoryMapper
import com.example.lawerapp.Retrofit.Mappers.UserMaper
import com.example.lawerapp.Retrofit.RetrofitInterface
import com.example.lawerapp.Utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Error

class CategoryRepository constructor(
    private val retrofitInterface: RetrofitInterface,
    private val categoryMaper: CategoryMapper
) {
    suspend fun getCategory() :Flow<DataState<List<CategoryModel>>> = flow {
        emit(DataState.Loading)
        try {
            val categoryNetwork=retrofitInterface.getCategory()
            val categoriesEntity=categoryNetwork.results
            val categoriesModels=categoryMaper.mapfromEntityList(categoriesEntity)
            emit(DataState.Success(categoriesModels))
        }catch (e : Exception){
            Log.e("CategoryRepository"," error "+e.message  )
            emit(DataState.Error(e))
        }

    }
}