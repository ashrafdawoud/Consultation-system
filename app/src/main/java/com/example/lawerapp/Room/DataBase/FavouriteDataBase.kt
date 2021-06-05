package com.example.lawerapp.Room.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lawerapp.Room.FavouritDao
import com.example.lawerapp.Room.Tables.FavouriteTable

@Database(entities = [FavouriteTable::class ], version = 1)
abstract class FavouriteDataBase :RoomDatabase() {
    abstract fun favouriteDao(): FavouritDao

    companion object{
        val DATABASE_NAME: String = "favourite_db"
    }
}