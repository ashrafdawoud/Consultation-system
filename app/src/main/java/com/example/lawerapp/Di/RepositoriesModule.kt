package com.example.lawerapp.Di

import com.example.lawerapp.Repository.*
import com.example.lawerapp.Retrofit.Mappers.CategoryMapper
import com.example.lawerapp.Retrofit.Mappers.GovernmentMapper
import com.example.lawerapp.Retrofit.Mappers.LawyerMaper
import com.example.lawerapp.Retrofit.Mappers.UserMaper
import com.example.lawerapp.Retrofit.RetrofitInterface
import com.example.lawerapp.Room.FavouritDao
import com.example.lawerapp.Room.Maper.FavouriteMaper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoriesModule  {
    @Singleton
    @Provides
    fun provideUserReposetory(
        retrofitInterface: RetrofitInterface,
        categoryMapper: CategoryMapper
    ) : CategoryRepository{
        return CategoryRepository(retrofitInterface,categoryMapper)
    }
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
    @Singleton
    @Provides
    fun provideUserRepository(
        retrofitInterface: RetrofitInterface,
        userMaper: UserMaper
    ): UserRepository {
        return UserRepository(retrofitInterface,userMaper)
    }
    @Singleton
    @Provides
    fun provideFavouriteRepository(
        favouritDao: FavouritDao,
        favouriteMaper: FavouriteMaper
    ):FavouriteRepository{
        return FavouriteRepository(favouritDao,favouriteMaper)
    }
    @Singleton
    @Provides
    fun provideGovernmentRepository(
        governmentMapper: GovernmentMapper,
        retrofitInterface: RetrofitInterface
    ):GovernmentRepository{
        return GovernmentRepository(governmentMapper,retrofitInterface)
    }
    @Singleton
    @Provides
    fun provideSearchRepository(
        retrofitInterface: RetrofitInterface,
        lawyerMaper: LawyerMaper
    ):SearchRepository{
        return SearchRepository(retrofitInterface,lawyerMaper)
    }
}