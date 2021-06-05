package com.example.lawerapp.Di

import android.content.Context
import androidx.room.Room
import com.example.lawerapp.Room.DataBase.FavouriteDataBase
import com.example.lawerapp.Room.FavouritDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideFavDb(@ApplicationContext context: Context): FavouriteDataBase {
        return Room
            .databaseBuilder(
                context,
                FavouriteDataBase::class.java,
                FavouriteDataBase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideFavDAO(favouriteDataBase: FavouriteDataBase): FavouritDao {
        return favouriteDataBase.favouriteDao()
    }
}