package com.example.lawerapp.Model

data class CategoryModelResult (
    var results:List<CategoryModel>
)
data class CategoryModel(
    var objectId : String,
    var name : String,
    var image : String,
    var informations : String,
)