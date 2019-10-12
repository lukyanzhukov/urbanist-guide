package com.lukianbat.urbanist.guide.сore.dagger.module

import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import okhttp3.logging.HttpLoggingInterceptor
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import com.lukianbat.urbanist.guide.feature.list.domain.model.Places
import com.lukianbat.urbanist.guide.feature.map.domain.model.RouteModel
import com.lukianbat.urbanist.guide.feature.list.data.datasource.api.PlacesDeserializer
import com.lukianbat.urbanist.guide.feature.map.domain.RouteDeserializer


@Module
class RetrofitModule {

    private val SightsafariClient = OkHttpClient.Builder()
        .followSslRedirects(true)
        .addInterceptor(getLoggingInterceptor()).build()

    private val GraphhopperClient = OkHttpClient.Builder()
        .followSslRedirects(true)
        .addInterceptor(getLoggingInterceptor())
        .addInterceptor(getGraphhopperInterceptor())
        .build()

    @Provides
    @Named("graphhopper")
    fun providesGoogleRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://graphhopper.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create(getRouteDeserializer()))
            .client(GraphhopperClient)
            .build()

    @Provides
    @Named("sightsafari")
    fun providesSightsafariRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://sightsafari.city/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create(getPlacesDeserializer()))
            .client(SightsafariClient)
            .build()

    private fun getLoggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private fun getGraphhopperInterceptor() =
        Interceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("key", "6f8e6fc9-71b4-402f-976e-2ac8aabf5c3c")
                .addQueryParameter("vehicle", "foot")
                .addQueryParameter("locale", "ru")
                .addQueryParameter("optimize", "true")
                .addQueryParameter("instructions", "false")
                .build()
            chain.proceed(
                original.newBuilder()
                    .url(url).build()
            )

        }

    private fun getPlacesDeserializer(): Gson = GsonBuilder()
        .registerTypeAdapter(
            Places::class.java,
            PlacesDeserializer()
        )
        .create()

    private fun getRouteDeserializer(): Gson = GsonBuilder()
        .registerTypeAdapter(
            RouteModel::class.java,
            RouteDeserializer()
        )
        .create()

}