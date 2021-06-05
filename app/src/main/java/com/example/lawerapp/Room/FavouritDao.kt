package com.example.lawerapp.Room

import androidx.room.*
import com.example.lawerapp.Model.LayersModel
import com.example.lawerapp.Room.Tables.FavouriteTable


@Dao
interface FavouritDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFav(favouriteTable: FavouriteTable) : Long
    @Delete
    suspend fun deleteRow(favouriteTable: FavouriteTable) : Int
    @Query("SELECT * FROM LayersTable")
    suspend fun getAllLayers(): List< FavouriteTable>
    @Query("SELECT * FROM LayersTable WHERE objectId=:objectid")
    suspend fun getoneLayer(objectid:String): List< FavouriteTable>
}