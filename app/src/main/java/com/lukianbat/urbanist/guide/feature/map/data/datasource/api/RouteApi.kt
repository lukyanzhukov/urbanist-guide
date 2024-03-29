package com.lukianbat.urbanist.guide.feature.map.data.datasource.api

import com.lukianbat.urbanist.guide.feature.map.domain.model.RouteModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RouteApi {

    @GET("/api/1/route")
    fun getRoute(
        @Query("point") points: List<String>
    ): Single<RouteModel>

}