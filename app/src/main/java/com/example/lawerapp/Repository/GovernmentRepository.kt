package com.example.lawerapp.Repository

import com.example.lawerapp.Model.GovernmentModel
import com.example.lawerapp.Model.LayersModel
import com.example.lawerapp.Retrofit.Mappers.GovernmentMapper
import com.example.lawerapp.Retrofit.RetrofitInterface
import com.example.lawerapp.Utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GovernmentRepository constructor(
    var governmentMapper: GovernmentMapper,
    var retrofitInterface: RetrofitInterface
) {
    fun get_government():Flow<DataState<List<GovernmentModel>>> = flow{
        emit(DataState.Loading)
        try {
            var datanetwork=retrofitInterface.getGovernment()
            var dataentities=datanetwork.results
            var dataModels=governmentMapper.mapfromentitylist(dataentities)
            emit(DataState.Success(dataModels))
        }catch (e:Exception){
            emit(DataState.Error(e))
        }
    }

}