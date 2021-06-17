package com.example.lawerapp.Repository

import android.util.Log
import com.example.lawerapp.Model.CalenderModel
import com.example.lawerapp.Retrofit.Entities.SucssesEntity
import com.example.lawerapp.Retrofit.Mappers.CalenderMapper
import com.example.lawerapp.Retrofit.Mappers.CategoryMapper
import com.example.lawerapp.Retrofit.RetrofitInterface
import com.example.lawerapp.Utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CalenderRepository constructor(
    private val retrofitInterface: RetrofitInterface,
    private val calenderMapper: CalenderMapper
) {
    suspend fun getreservedDates(objectid:String,date:String) :Flow<DataState<List<CalenderModel>>> = flow {
        emit(DataState.Loading)
        try {
            Log.e("datareversation","{\"lawyer_id\":\""+objectid+"\",\"date\":\""+date+"\"}")
            val calenderNetwork =
                retrofitInterface.getreservdata("{\"lawyer_id\":\""+objectid+"\",\"date\":\""+date+"\"}")
            val calenderEntityList = calenderNetwork.results
            val calenderModelList = calenderMapper.mapfromEntityList(calenderEntityList)
            Log.e("datareversation",calenderModelList.get(0).date)
            emit(DataState.Success(calenderModelList))
        }catch (e:Exception){
            emit(DataState.Error(e))

        }
    }
    suspend fun postappointment(map : HashMap<String,String>):Flow<DataState<SucssesEntity>> = flow{
        emit(DataState.Loading)
        try {
            val res=retrofitInterface.appointmentfunction(map)
            Log.e("appointmentrepo", "respond " + res.objectId )
            emit(DataState.Success(res))
        }catch (e: Exception){
            emit(DataState.Error(e))
        }

    }
}