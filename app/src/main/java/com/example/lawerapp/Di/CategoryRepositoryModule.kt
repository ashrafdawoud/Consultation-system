package com.example.lawerapp.Di

import com.example.lawerapp.Repository.CategoryRepository
import com.example.lawerapp.Retrofit.Mappers.CategoryMapper
import com.example.lawerapp.Retrofit.RetrofitInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object CategoryRepositoryModule  {
    @Singleton
    @Provides
    fun provideUserReposetory(
        retrofitInterface: RetrofitInterface,
        categoryMapper: CategoryMapper
    ) : CategoryRepository{
        return CategoryRepository(retrofitInterface,categoryMapper)
    }
}