package com.lukianbat.urbanist.urbanist_guide.сore.network

import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class RetrofitModule {

    private val okHttpClient = OkHttpClient.Builder()
        .followSslRedirects(true)
        .addInterceptor(getInterceptor())
        .build()


    @Provides
    @Singleton
    @Named("google")
    fun providesGoogleRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://maps.googleapis.com/maps/api/directions/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    @Named("sightsafari")
    fun providesSightsafariRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://sightsafari.city/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    private fun getInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}