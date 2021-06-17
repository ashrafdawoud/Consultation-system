package com.example.lawerapp.Retrofit.Mappers

import com.example.lawerapp.Model.CalenderModel
import com.example.lawerapp.Retrofit.Entities.CalenderEntity
import com.example.lawerapp.Utils.EntityMaper
import javax.inject.Inject

class CalenderMapper @Inject constructor(): EntityMaper<CalenderEntity,CalenderModel>{
    override fun mapFromEntity(entity: CalenderEntity): CalenderModel {
        return CalenderModel(
            objectId = entity.objectId,
            date = entity.date,
            time = entity.time
        )
    }

    override fun mapToEntity(domainModel: CalenderModel): CalenderEntity {
        return CalenderEntity(
            objectId = domainModel.objectId,
            date = domainModel.date,
            time = domainModel.time
        )
    }
    fun mapfromEntityList(entities:List<CalenderEntity>):List<CalenderModel>{
        return entities.map { mapFromEntity(it) }

    }
}