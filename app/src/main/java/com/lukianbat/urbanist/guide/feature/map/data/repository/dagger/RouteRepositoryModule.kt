package com.lukianbat.urbanist.guide.feature.map.data.repository.dagger

import com.lukianbat.urbanist.guide.feature.map.data.datasource.api.RouteApiDataSource
import com.lukianbat.urbanist.guide.feature.map.data.datasource.api.dagger.ApiRouteModule
import com.lukianbat.urbanist.guide.feature.map.data.datasource.cache.CacheRouteDataSource
import com.lukianbat.urbanist.guide.feature.map.data.datasource.cache.dagger.CacheRouteModule
import com.lukianbat.urbanist.guide.feature.map.data.repository.RouteRepository
import com.lukianbat.urbanist.guide.feature.map.data.repository.RouteRepositoryImpl
import dagger.Module
import dagger.Provides

@Module(includes = [ApiRouteModule::class, CacheRouteModule::class])
class RouteRepositoryModule {

    @Provides
    fun provideRepository(apiDataSource: RouteApiDataSource, cacheDataSource: CacheRouteDataSource):
            RouteRepository = RouteRepositoryImpl(cacheDataSource, apiDataSource)
}