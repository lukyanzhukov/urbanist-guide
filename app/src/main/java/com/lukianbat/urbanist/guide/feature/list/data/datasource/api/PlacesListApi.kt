package com.lukianbat.urbanist.guide.feature.list.data.datasource.api

import com.lukianbat.urbanist.guide.feature.list.domain.model.Places
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesListApi {

    @GET("/api/v1/geography/sights")
    fun getPlaces(@Query("lat") lat: Double, @Query("lon") lng: Double): Single<Places>

}