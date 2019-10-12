package com.lukianbat.urbanist.guide.feature.start.domain.usecases.dagger

import com.lukianbat.urbanist.guide.feature.start.data.repository.CityRepository
import com.lukianbat.urbanist.guide.feature.start.data.repository.dagger.CityRepositoryModule
import com.lukianbat.urbanist.guide.feature.start.domain.usecases.GetCityNameUseCase
import com.lukianbat.urbanist.guide.feature.start.domain.usecases.GetCityNameUseCaseImpl
import com.lukianbat.urbanist.guide.feature.start.domain.usecases.SetCityNameUseCase
import com.lukianbat.urbanist.guide.feature.start.domain.usecases.SetCityNameUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [CityRepositoryModule::class])
class CityNameUseCaseModule {

    @Provides
    fun provideGetNumberUseCase(repository: CityRepository): GetCityNameUseCase =
        GetCityNameUseCaseImpl(repository)

    @Provides
    fun provideSetNumberUseCase(repository: CityRepository): SetCityNameUseCase =
        SetCityNameUseCaseImpl(repository)
}