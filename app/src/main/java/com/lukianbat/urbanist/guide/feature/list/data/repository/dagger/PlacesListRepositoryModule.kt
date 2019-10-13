package com.lukianbat.urbanist.guide.feature.list.data.repository.dagger


import com.lukianbat.urbanist.guide.feature.list.data.datasource.api.PlacesListApiDataSource
import com.lukianbat.urbanist.guide.feature.list.data.datasource.api.dagger.PlacesListApiModule
import com.lukianbat.urbanist.guide.feature.list.data.datasource.db.PlacesListCacheDataSource
import com.lukianbat.urbanist.guide.feature.list.data.datasource.db.dagger.PlacesListCacheModule
import com.lukianbat.urbanist.guide.feature.list.data.repository.PlacesListRepository
import com.lukianbat.urbanist.guide.feature.list.data.repository.PlacesListRepositoryImpl
import com.lukianbat.urbanist.guide.feature.start.data.datasource.CityDataSource
import dagger.Module
import dagger.Provides

@Module(includes = [PlacesListApiModule::class, PlacesListCacheModule::class])
class PlacesListRepositoryModule {

    @Provides
    fun provideListRepository(
        apiDataSource: PlacesListApiDataSource,
        cacheDataSource: PlacesListCacheDataSource,
        cityDataSource: CityDataSource
    ): PlacesListRepository =
        PlacesListRepositoryImpl(cacheDataSource, apiDataSource, cityDataSource)

}