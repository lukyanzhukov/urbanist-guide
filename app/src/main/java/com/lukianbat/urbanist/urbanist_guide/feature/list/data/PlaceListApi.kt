package com.lukianbat.urbanist.urbanist_guide.feature.list.data

import com.lukianbat.urbanist.urbanist_guide.feature.list.domain.model.Places
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceListApi {

    @GET("/api/v1/geography/sights")
    fun getPlaces(@Query("lat") lat: Double, @Query("lon") lng: Double): Single<Places>

}