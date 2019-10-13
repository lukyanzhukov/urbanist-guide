package com.lukianbat.urbanist.guide.feature.map.data.datasource.api.dagger

import com.lukianbat.urbanist.guide.feature.map.data.datasource.api.RouteApi
import com.lukianbat.urbanist.guide.feature.map.data.datasource.api.RouteApiDataSourceImpl
import com.lukianbat.urbanist.guide.feature.map.data.datasource.api.RouteApiDataSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class ApiRouteModule
{

    @Provides
    fun provideApi(@Named("graphhopper") retrofit: Retrofit): RouteApi =
        retrofit.create(RouteApi::class.java)

    @Provides
    fun provideDataSource(routeApi: RouteApi): RouteApiDataSource =
        RouteApiDataSourceImpl(routeApi)
}