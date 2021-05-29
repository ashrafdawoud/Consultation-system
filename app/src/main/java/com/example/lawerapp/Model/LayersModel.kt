package com.example.lawerapp.Model

import java.io.Serializable

data class LayersModel (
    val objectId:String,
    val name:String,
    val address:String,
    val exp:String,
    val discreiption:String,
    val cort_location:String,
    val language:String,
    val popular:Boolean,
    val category:String,
    val image:String,
    val price:String,
) : Serializable