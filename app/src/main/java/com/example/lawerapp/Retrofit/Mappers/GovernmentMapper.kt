package com.example.lawerapp.Retrofit.Mappers

import com.example.lawerapp.Model.GovernmentModel
import com.example.lawerapp.Retrofit.Entities.GovernmentEntity
import com.example.lawerapp.Utils.EntityMaper
import javax.inject.Inject

class GovernmentMapper @Inject constructor() : EntityMaper<GovernmentEntity,GovernmentModel> {
    override fun mapFromEntity(entity: GovernmentEntity): GovernmentModel {
        return GovernmentModel(
            objectId = entity.objectId,
            goverment = entity.government,
            cites = entity.Cites
        )
    }

    override fun mapToEntity(domainModel: GovernmentModel): GovernmentEntity {
        return GovernmentEntity(
            objectId = domainModel.objectId,
            government = domainModel.goverment,
            Cites = domainModel.cites
        )
    }
    fun mapfromentitylist(entity: List<GovernmentEntity>) : List<GovernmentModel> {
        return entity.map { mapFromEntity(it) }
    }
}