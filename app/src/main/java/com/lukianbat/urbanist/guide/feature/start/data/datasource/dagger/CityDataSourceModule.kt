package com.lukianbat.urbanist.guide.feature.start.data.datasource.dagger

import android.content.SharedPreferences
import com.lukianbat.urbanist.guide.feature.start.data.datasource.CityDataSource
import com.lukianbat.urbanist.guide.feature.start.data.datasource.CityDataSourceImpl
import dagger.Module
import dagger.Provides

@Module
class CityDataSourceModule {

    @Provides
    fun provideDataSource(preferences: SharedPreferences): CityDataSource =
        CityDataSourceImpl(preferences)

}