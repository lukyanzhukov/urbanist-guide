package com.lukianbat.urbanist.urbanist_guide.feature.map.di

import com.lukianbat.urbanist.urbanist_guide.feature.list.domain.repository.MapRepository
import com.lukianbat.urbanist.urbanist_guide.feature.map.data.CacheRouteDao
import com.lukianbat.urbanist.urbanist_guide.feature.map.domain.repository.MapRepositoryImpl
import com.lukianbat.urbanist.urbanist_guide.feature.map.data.MapApi
import com.lukianbat.urbanist.urbanist_guide.сore.dagger.scope.ActivityScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class MapNetworkModule {

    @Provides
    @ActivityScope
    fun provideApi(@Named("graphhopper") retrofit: Retrofit): MapApi = retrofit.create(MapApi::class.java)

    @Provides
    @ActivityScope
    fun provideRepository(mapApi: MapApi, dao: CacheRouteDao): MapRepository = MapRepositoryImpl(mapApi, dao)
}