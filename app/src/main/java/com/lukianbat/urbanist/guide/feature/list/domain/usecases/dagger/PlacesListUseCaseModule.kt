package com.lukianbat.urbanist.guide.feature.list.domain.usecases.dagger

import com.lukianbat.urbanist.guide.feature.list.data.repository.dagger.PlacesListRepositoryModule
import com.lukianbat.urbanist.guide.feature.list.domain.usecases.GetPlacesUseCase
import com.lukianbat.urbanist.guide.feature.list.domain.usecases.GetPlacesUseCaseImpl
import com.lukianbat.urbanist.guide.feature.list.domain.usecases.SetPlacesUseCase
import com.lukianbat.urbanist.guide.feature.list.domain.usecases.SetPlacesUseCaseImpl
import dagger.Binds
import dagger.Module

@Module(includes = [PlacesListRepositoryModule::class])
interface PlacesListUseCaseModule {

    @Binds
    fun bindGetPlacesUseCase(impl: GetPlacesUseCaseImpl): GetPlacesUseCase

    @Binds
    fun bindSetPlacesUseCase(impl: SetPlacesUseCaseImpl): SetPlacesUseCase
}