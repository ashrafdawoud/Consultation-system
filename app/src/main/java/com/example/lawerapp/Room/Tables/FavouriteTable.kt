package com.example.lawerapp.Room.Tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LayersTable")
class FavouriteTable (
    @PrimaryKey
    @ColumnInfo(name = "objectId")
    val objectId:String,
    @ColumnInfo(name = "name")
    val name:String,
    @ColumnInfo(name = "address")
    val address:String,
    @ColumnInfo(name = "exp")
    val exp:String,
    @ColumnInfo(name = "discreiption")
    val discreiption:String,
    @ColumnInfo(name = "cort_location")
    val cort_location:String,
    @ColumnInfo(name = "language")
    val language:String,
    @ColumnInfo(name = "popular")
    val popular:Boolean,
    @ColumnInfo(name = "category")
    val category:String,
    @ColumnInfo(name = "image")
    val image:String,
    @ColumnInfo(name = "price")
    val price:String,

)