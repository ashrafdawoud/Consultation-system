package com.example.lawerapp.Room.Maper

import com.example.lawerapp.Model.LayersModel
import com.example.lawerapp.Room.Tables.FavouriteTable
import com.example.lawerapp.Utils.EntityMaper
import javax.inject.Inject

class FavouriteMaper @Inject constructor() : EntityMaper<FavouriteTable, LayersModel> {
    override fun mapFromEntity(entity: FavouriteTable): LayersModel {
        return LayersModel(
            objectId = entity.objectId,
            name = entity.name,
            address = entity.address,
            exp = entity.exp,
            discreiption = entity.discreiption,
            cort_location = entity.cort_location,
            language = entity.language,
            popular = entity.popular,
            category = entity.category,
            image = entity.image,
            price = entity.price,
        )
    }

    override fun mapToEntity(domainModel: LayersModel): FavouriteTable {
        return FavouriteTable(
            objectId = domainModel.objectId,
            name = domainModel.name,
            address = domainModel.address,
            exp = domainModel.exp,
            discreiption = domainModel.discreiption,
            cort_location = domainModel.cort_location,
            language = domainModel.language,
            popular = domainModel.popular,
            category = domainModel.category,
            image = domainModel.image,
            price = domainModel.price,
        )
    }
    fun mapfromEntityList(entities:List<FavouriteTable>):List<LayersModel>{
        return entities.map { mapFromEntity(it) }
    }
}