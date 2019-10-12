package com.lukianbat.urbanist.guide.сore.dagger.module

import com.lukianbat.urbanist.guide.feature.map.presentation.ViewModelModule
import com.lukianbat.urbanist.guide.feature.start.data.datasource.dagger.CityDataSourceModule
import com.lukianbat.urbanist.guide.сore.dagger.module.feature.MapsModule
import com.lukianbat.urbanist.guide.сore.dagger.module.feature.PlacesListModule
import com.lukianbat.urbanist.guide.сore.dagger.module.feature.StartModule
import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule

@Module(
    includes = [
        AndroidSupportInjectionModule::class,
        RetrofitModule::class,
        PreferenceModule::class,
        ViewModelModule::class,
        CityDataSourceModule::class,
        RoomModule::class,
        StartModule::class,
        PlacesListModule::class,
        MapsModule::class
    ]
)
interface ApplicationModule