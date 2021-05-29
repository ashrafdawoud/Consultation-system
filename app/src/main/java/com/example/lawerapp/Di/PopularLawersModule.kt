package com.example.lawerapp.Di

import com.example.lawerapp.Repository.PopularLawersRepository
import com.example.lawerapp.Retrofit.Mappers.LawyerMaper
import com.example.lawerapp.Retrofit.RetrofitInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object PopularLawersModule {

    @Singleton
    @Provides
    fun providepopularRepository(
        retrofitInterface: RetrofitInterface,
        popularLawyerMaper: LawyerMaper
    ):PopularLawersRepository{
        return PopularLawersRepository(
            retrofitInterface,
            popularLawyerMaper,
        )
    }
}