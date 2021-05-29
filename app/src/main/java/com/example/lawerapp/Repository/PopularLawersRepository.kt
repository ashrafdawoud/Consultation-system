package com.example.lawerapp.Repository

import com.example.lawerapp.Model.LayersModel
import com.example.lawerapp.Retrofit.Mappers.LawyerMaper
import com.example.lawerapp.Retrofit.RetrofitInterface
import com.example.lawerapp.Utils.DataState
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class PopularLawersRepository  constructor(
    private val retrofitInterface: RetrofitInterface,
    private val popularLawyerMaper: LawyerMaper
){
    suspend fun getpopularLawyers() : kotlinx.coroutines.flow.Flow<DataState<List<LayersModel>>> = flow{
        emit(DataState.Loading)
        try {
            val popularnetwork= retrofitInterface.getpopularLawers()
            val popularEntites=popularnetwork.results
            val popularmodels=popularLawyerMaper.mapfromEntityList(popularEntites)
            emit(DataState.Success(popularmodels))
        }catch (e : Exception){
            emit(DataState.Error(e))
        }
    }
    suspend fun getcriminalLawyers() : kotlinx.coroutines.flow.Flow<DataState<List<LayersModel>>> = flow{
        emit(DataState.Loading)
        try {
            val popularnetwork= retrofitInterface.getCrimanalLawers()
            val popularEntites=popularnetwork.results
            val popularmodels=popularLawyerMaper.mapfromEntityList(popularEntites)
            emit(DataState.Success(popularmodels))
        }catch (e : Exception){
            emit(DataState.Error(e))
        }
    }
    suspend fun getCategoryLawyers(id:String) : kotlinx.coroutines.flow.Flow<DataState<List<LayersModel>>> = flow{
        emit(DataState.Loading)
        try {
            val popularnetwork= retrofitInterface.getCategoryLawers("{\"category\" : \""+id+"\"}")
            val popularEntites=popularnetwork.results
            val popularmodels=popularLawyerMaper.mapfromEntityList(popularEntites)
            emit(DataState.Success(popularmodels))
        }catch (e : Exception){
            emit(DataState.Error(e))
        }
    }
}