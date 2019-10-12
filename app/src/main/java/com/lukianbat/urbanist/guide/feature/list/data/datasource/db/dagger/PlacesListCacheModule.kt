package com.lukianbat.urbanist.guide.feature.list.data.datasource.db.dagger

import com.lukianbat.urbanist.guide.feature.list.data.datasource.db.CachePlacesDao
import com.lukianbat.urbanist.guide.feature.list.data.datasource.db.PlacesListCacheDataSource
import com.lukianbat.urbanist.guide.feature.list.data.datasource.db.PlacesListCacheDataSourceImpl
import com.lukianbat.urbanist.guide.сore.data.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class PlacesListCacheModule {

    @Provides
    fun provideCachePlacesDao(appDatabase: AppDatabase): CachePlacesDao =
        appDatabase.cashPlacesDao()

    @Provides
    fun provideCacheDataSource(dao: CachePlacesDao): PlacesListCacheDataSource =
        PlacesListCacheDataSourceImpl(dao)
}