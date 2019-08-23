package com.lukianbat.urbanist.guide.сore.data.di

import android.content.Context
import androidx.room.Room
import com.lukianbat.urbanist.guide.сore.data.AppDatabase
import com.lukianbat.urbanist.guide.feature.list.data.CachePlacesDao
import com.lukianbat.urbanist.guide.feature.map.data.CacheRouteDao
import dagger.Module
import dagger.Provides

@Module
class RoomModule {

    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
    }

    @Provides
    fun provideCashPlacesDao(appDatabase: AppDatabase): CachePlacesDao {
        return appDatabase.cashPlacesDao()
    }

    @Provides
    fun provideCashRouteDao(appDatabase: AppDatabase): CacheRouteDao {
        return appDatabase.cashRouteDao()
    }
}