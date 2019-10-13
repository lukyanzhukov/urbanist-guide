package com.lukianbat.urbanist.guide.feature.map.domain.usecases.dagger

import com.lukianbat.urbanist.guide.feature.map.data.repository.RouteRepository
import com.lukianbat.urbanist.guide.feature.map.data.repository.dagger.RouteRepositoryModule
import com.lukianbat.urbanist.guide.feature.map.domain.usecases.GetRouteUseCase
import com.lukianbat.urbanist.guide.feature.map.domain.usecases.GetRouteUseCaseImpl
import dagger.Module
import dagger.Provides

@Module(includes = [RouteRepositoryModule::class])
class GetRouteUseCaseModule {

    @Provides
    fun provideUseCase(repository: RouteRepository): GetRouteUseCase =
        GetRouteUseCaseImpl(repository)
}