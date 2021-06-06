package com.example.lawerapp.Repository

import android.util.Log
import com.example.lawerapp.Model.LayersModel
import com.example.lawerapp.Retrofit.Mappers.LawyerMaper
import com.example.lawerapp.Retrofit.RetrofitInterface
import com.example.lawerapp.Utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRepository constructor(
    val retrofitInterface: RetrofitInterface,
    val lawyerMaper: LawyerMaper
) {

    fun get_lawyers_without_with_name(
        city: String,
        category: String,
        name: String
    ): Flow<DataState<List<LayersModel>>> = flow {
        emit(DataState.Loading)

        try {

            if (!name.equals("")) {
                Log.e("namename",name)
                var datanetwork =
                    retrofitInterface.searchbyname("{\"city\":\"" + city + "\",\"category\":\"" + category + "\",\"name\":\"" + name + "\"}")
                var layersentity = datanetwork.results
                var layermodel = lawyerMaper.mapfromEntityList(layersentity)
                emit(DataState.Success(layermodel))
            } else {
                var datanetwork =
                    retrofitInterface.searchbyname("{\"city\":\"" + city + "\",\"category\":\"" + category + "\"}")
                var layersentity = datanetwork.results
                var layermodel = lawyerMaper.mapfromEntityList(layersentity)
                emit(DataState.Success(layermodel))
            }

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}