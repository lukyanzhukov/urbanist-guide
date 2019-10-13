package com.lukianbat.urbanist.guide.feature.map.data.datasource.api

import com.lukianbat.urbanist.guide.feature.map.domain.model.RouteModel
import io.reactivex.Single
import javax.inject.Inject

interface RouteApiDataSource {
    fun getRoute(
        points: List<String>
    ): Single<RouteModel>
}

class RouteApiDataSourceImpl @Inject constructor(private val api: RouteApi) : RouteApiDataSource {

    override fun getRoute(points: List<String>): Single<RouteModel> = api.getRoute(points)

}