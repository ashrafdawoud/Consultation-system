package com.example.lawerapp.Repository

import com.example.lawerapp.Model.LayersModel
import com.example.lawerapp.Room.FavouritDao
import com.example.lawerapp.Room.Maper.FavouriteMaper
import com.example.lawerapp.Room.Tables.FavouriteTable
import com.example.lawerapp.Utils.DataState
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow

class FavouriteRepository
constructor(
    var favouritDao: FavouritDao,
    var favouriteMaper: FavouriteMaper
) {
    fun getAllFavLayers(): kotlinx.coroutines.flow.Flow<DataState<List<LayersModel>>> = flow {
        emit(DataState.Loading)
        try {
            var FavLayersEntities = favouritDao.getAllLayers()
            var favouriteModules = favouriteMaper.mapfromEntityList(FavLayersEntities)
            emit(DataState.Success(favouriteModules))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    fun getoneLayer(objectid:String): kotlinx.coroutines.flow.Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            var FavLayersEntities = favouritDao.getoneLayer(objectid)
            var favouriteModules = favouriteMaper.mapfromEntityList(FavLayersEntities)
            if (favouriteModules.size != 0)
                emit(DataState.Success(true))
            else
                emit(DataState.Success(false))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    fun deleteFavLawyer(layersModel: LayersModel): kotlinx.coroutines.flow.Flow<DataState<Int>> =
        flow {
            emit(DataState.Loading)
            try {
                val favouriteTable = favouriteMaper.mapToEntity(layersModel)
                val int = favouritDao.deleteRow(favouriteTable)
                emit(DataState.Success(int))

            } catch (e: java.lang.Exception) {
                emit(DataState.Error(e))
            }
        }

    fun insertFavLawyer(layersModel: LayersModel): kotlinx.coroutines.flow.Flow<DataState<Long>> =
        flow {
            emit(DataState.Loading)
            try {
                val favouriteTable = favouriteMaper.mapToEntity(layersModel)
                val long = favouritDao.insertFav(favouriteTable)
                emit(DataState.Success(long))

            } catch (e: java.lang.Exception) {
                emit(DataState.Error(e))
            }
        }
}