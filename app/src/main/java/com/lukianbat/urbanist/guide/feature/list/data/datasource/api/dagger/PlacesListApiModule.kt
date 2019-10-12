package com.lukianbat.urbanist.guide.feature.list.data.datasource.api.dagger

import com.lukianbat.urbanist.guide.feature.list.data.datasource.api.PlacesListApi
import com.lukianbat.urbanist.guide.feature.list.data.datasource.api.PlacesListApiDataSource
import com.lukianbat.urbanist.guide.feature.list.data.datasource.api.PlacesListApiDataSourceImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class PlacesListApiModule {

    @Provides
    fun provideListApi(@Named("sightsafari") retrofit: Retrofit): PlacesListApi = retrofit.create(
        PlacesListApi::class.java
    )

    @Provides
    fun provideListApiDataSource(api: PlacesListApi): PlacesListApiDataSource =
        PlacesListApiDataSourceImpl(api)
}