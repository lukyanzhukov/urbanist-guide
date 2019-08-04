package com.lukianbat.urbanist.urbanist_guide.feature.map.domain.repository

import com.lukianbat.urbanist.urbanist_guide.feature.list.domain.repository.MapRepository
import com.lukianbat.urbanist.urbanist_guide.feature.map.data.MapApi
import com.lukianbat.urbanist.urbanist_guide.feature.map.domain.model.Point
import com.lukianbat.urbanist.urbanist_guide.feature.map.domain.model.RouteModel
import io.reactivex.Single

class MapRepositoryImpl(private val mapApi: MapApi) : MapRepository {
    override fun getRoute(points: List<Point>): Single<RouteModel> {
        val pointsList = arrayListOf<String>()
        points.forEach {
            pointsList.add(it.lat.toString() + "," + it.lng.toString())
        }
        return mapApi.getRoute(pointsList)
    }
}