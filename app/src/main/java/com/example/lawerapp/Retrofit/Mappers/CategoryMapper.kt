package com.example.lawerapp.Retrofit.Mappers

import com.example.lawerapp.Model.CategoryModel
import com.example.lawerapp.Retrofit.Entities.CategoryEntity
import com.example.lawerapp.Retrofit.Entities.CategoryRetrofitEntity
import com.example.lawerapp.Utils.EntityMaper
import javax.inject.Inject

class CategoryMapper @Inject constructor()  : EntityMaper<CategoryEntity,CategoryModel>{
    override fun mapFromEntity(entity: CategoryEntity): CategoryModel {
        return CategoryModel(
            objectId = entity.objectId,
            name = entity.Name,
            image = entity.image,
            informations = entity.informations

        )
    }

    override fun mapToEntity(domainModel: CategoryModel): CategoryEntity {
        return CategoryEntity(
            objectId = domainModel.objectId,
            Name = domainModel.name,
            image = domainModel.image,
            informations = domainModel.informations
        )
    }
    fun mapfromEntityList(entity: List<CategoryEntity>):List<CategoryModel>{
        return entity.map { mapFromEntity(it) }
    }

}