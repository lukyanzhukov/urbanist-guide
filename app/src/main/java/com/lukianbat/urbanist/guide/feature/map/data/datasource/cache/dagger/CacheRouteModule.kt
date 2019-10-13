package com.lukianbat.urbanist.guide.feature.map.data.datasource.cache.dagger

import com.lukianbat.urbanist.guide.feature.map.data.datasource.cache.CacheRouteDao
import com.lukianbat.urbanist.guide.feature.map.data.datasource.cache.CacheRouteDataSource
import com.lukianbat.urbanist.guide.feature.map.data.datasource.cache.CacheRouteDataSourceImpl
import com.lukianbat.urbanist.guide.сore.data.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class CacheRouteModule {

    @Provides
    fun provideCashRouteDao(appDatabase: AppDatabase): CacheRouteDao {
        return appDatabase.cashRouteDao()
    }

    @Provides
    fun provideDataSource(dao: CacheRouteDao): CacheRouteDataSource =
        CacheRouteDataSourceImpl(dao)
}