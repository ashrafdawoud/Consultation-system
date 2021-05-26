package com.example.lawerapp.Retrofit.Mappers

import com.example.lawerapp.Model.UserModel
import com.example.lawerapp.Retrofit.Entities.User
import com.example.lawerapp.Retrofit.Entities.UserRetrofitEntity
import com.example.lawerapp.Utils.EntityMaper
import javax.inject.Inject

class UserMaper @Inject constructor() : EntityMaper<UserRetrofitEntity, UserModel> {
    override fun mapFromEntity(entity: UserRetrofitEntity): UserModel {
        return UserModel(
            objectId = entity.results.get(0).objectId,
            first_name = entity.results.get(0).first_name,
            second_name = entity.results.get(0).second_name,
            phone = entity.results.get(0).phone,
            email = entity.results.get(0).email,
            password = entity.results.get(0).password,

            )
    }

    override fun mapToEntity(domainModel: UserModel): UserRetrofitEntity {
        var user1 = User(
            objectId = domainModel.objectId,
            first_name = domainModel.first_name,
            second_name = domainModel.second_name,
            phone = domainModel.phone,
            email = domainModel.email,
            password = domainModel.password,
        )
        return UserRetrofitEntity(
            results = listOf(user1)
            /* */

        )
    }

    fun mapFromEntityList(entities: List<UserRetrofitEntity>): List<UserModel> {
        return entities.map { mapFromEntity(it) }
    }

}