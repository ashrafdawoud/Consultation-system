package com.example.lawerapp.Retrofit.Mappers

import com.example.lawerapp.Model.LayersModel
import com.example.lawerapp.Retrofit.Entities.PopularLawersitEntity
import com.example.lawerapp.Utils.EntityMaper
import javax.inject.Inject

class LawyerMaper @Inject constructor() : EntityMaper<PopularLawersitEntity,LayersModel> {
    override fun mapFromEntity(entity: PopularLawersitEntity): LayersModel {
        return LayersModel(
            objectId = entity.objectId,
            name = entity.name,
            address = entity.address,
            exp = entity.exp,
            cort_location = entity.cort_location,
            discreiption = entity.discreiption,
            language = entity.language,
            popular = entity.popular,
            category = entity.category,
            price = entity.price,
            image = entity.image
        )
    }

    override fun mapToEntity(domainModel: LayersModel): PopularLawersitEntity {
        return PopularLawersitEntity(
            objectId = domainModel.objectId,
            name = domainModel.name,
            address = domainModel.address,
            exp = domainModel.exp,
            cort_location = domainModel.cort_location,
            discreiption = domainModel.discreiption,
            language = domainModel.language,
            popular = domainModel.popular,
            category = domainModel.category,
            price = domainModel.price,
            image = domainModel.image
        )
    }
    fun mapfromEntityList(entity: List<PopularLawersitEntity>) :List<LayersModel>{
        return entity.map { mapFromEntity(it) }
    }
}