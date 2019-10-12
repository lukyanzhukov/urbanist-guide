package com.lukianbat.urbanist.guide.feature.list.presentation.dagger

import androidx.lifecycle.ViewModelProvider
import com.lukianbat.urbanist.guide.feature.list.domain.usecases.GetPlacesUseCase
import com.lukianbat.urbanist.guide.feature.list.domain.usecases.SetPlacesUseCase
import com.lukianbat.urbanist.guide.feature.list.presentation.PlacesListActivity
import com.lukianbat.urbanist.guide.feature.list.presentation.PlacesListViewModel
import com.lukianbat.urbanist.guide.сore.presentation.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PlacesListActivityModule {

    @Provides
    fun provideViewModel(
        context: PlacesListActivity,
        getPlacesUseCase: GetPlacesUseCase,
        setPlacesUseCase: SetPlacesUseCase
    ): PlacesListViewModel = ViewModelFactory {
        PlacesListViewModel(
            getPlacesUseCase,
            setPlacesUseCase
        )
    }.let { viewModelFactory ->
        ViewModelProvider(context, viewModelFactory)[PlacesListViewModel::class.java]
    }

}