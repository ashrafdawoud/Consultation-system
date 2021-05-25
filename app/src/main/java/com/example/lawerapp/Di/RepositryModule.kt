package com.example.lawerapp.Di

import com.example.lawerapp.Repository.UserRepository
import com.example.lawerapp.Retrofit.Mappers.UserMaper
import com.example.lawerapp.Retrofit.RetrofitInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
         retrofitInterface: RetrofitInterface,
         userMaper: UserMaper
    ):UserRepository{
        return UserRepository(retrofitInterface,userMaper)
    }
}