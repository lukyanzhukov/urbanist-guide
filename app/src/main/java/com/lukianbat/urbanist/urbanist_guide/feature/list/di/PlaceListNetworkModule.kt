package com.lukianbat.urbanist.urbanist_guide.feature.list.di

import com.lukianbat.urbanist.urbanist_guide.feature.list.data.CachePlacesDao
import com.lukianbat.urbanist.urbanist_guide.feature.list.data.PlaceListApi
import com.lukianbat.urbanist.urbanist_guide.feature.list.domain.repository.PlaceListRepository
import com.lukianbat.urbanist.urbanist_guide.feature.list.domain.repository.PlaceListRepositoryImpl
import com.lukianbat.urbanist.urbanist_guide.сore.dagger.scope.ActivityScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class PlaceListNetworkModule {

    @Provides
    @ActivityScope
    fun provideApi(@Named("sightsafari") retrofit: Retrofit): PlaceListApi = retrofit.create(PlaceListApi::class.java)

    @Provides
    @ActivityScope
    fun provideRepository(placeListApi: PlaceListApi, dao: CachePlacesDao): PlaceListRepository =
        PlaceListRepositoryImpl(placeListApi, dao)
}