package com.lukianbat.urbanist.guide.feature.start.data.repository.dagger

import com.lukianbat.urbanist.guide.feature.start.data.datasource.CityDataSource
import com.lukianbat.urbanist.guide.feature.start.data.repository.CityRepository
import com.lukianbat.urbanist.guide.feature.start.data.repository.CityRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class CityRepositoryModule {

    @Provides
    fun provideRepository(dataSource: CityDataSource): CityRepository =
        CityRepositoryImpl(dataSource)
}