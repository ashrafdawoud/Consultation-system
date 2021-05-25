package com.example.lawerapp.Retrofit.Mappers

import com.example.lawerapp.Model.UserModel
import com.example.lawerapp.Retrofit.Entities.UserRetrofitEntity
import com.example.lawerapp.Utils.EntityMaper
import javax.inject.Inject

class UserMaper @Inject constructor() :EntityMaper<UserRetrofitEntity,UserModel>{
    override fun mapFromEntity(entity: UserRetrofitEntity): UserModel {
        return UserModel(
            objectId = entity.objectId ,
            first_name = entity.first_name,
            second_name = entity.second_name,
            phone = entity.phone,
            email = entity.email,
            password = entity.password,

        )
    }

    override fun mapToEntity(domainModel: UserModel): UserRetrofitEntity {
        return UserRetrofitEntity(
            objectId = domainModel.objectId ,
            first_name = domainModel.first_name,
            second_name = domainModel.second_name,
            phone = domainModel.phone,
            email = domainModel.email,
            password = domainModel.password,

            )
    }
    fun mapFromEntityList(entities: List<UserRetrofitEntity>): List<UserModel>{
        return entities.map { mapFromEntity(it) }
    }

}