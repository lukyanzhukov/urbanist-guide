package com.lukianbat.urbanist.guide.feature.start.presentation.dagger

import androidx.lifecycle.ViewModelProvider
import com.lukianbat.urbanist.guide.feature.start.domain.usecases.GetCityNameUseCase
import com.lukianbat.urbanist.guide.feature.start.domain.usecases.SetCityNameUseCase
import com.lukianbat.urbanist.guide.feature.start.presentation.StartActivity
import com.lukianbat.urbanist.guide.feature.start.presentation.StartViewModule
import com.lukianbat.urbanist.guide.сore.presentation.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class StartActivityModule {

    @Provides
    fun provideViewModel(
        context: StartActivity,
        getCityNameUseCase: GetCityNameUseCase,
        setCityNameUseCase: SetCityNameUseCase
    ): StartViewModule = ViewModelFactory {
        StartViewModule(
            getCityNameUseCase,
            setCityNameUseCase
        )
    }.let { viewModelFactory ->
        ViewModelProvider(context, viewModelFactory)[StartViewModule::class.java]
    }
}