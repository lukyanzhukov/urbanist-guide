package com.lukianbat.urbanist.urbanist_guide.feature.list.domain.repository

import com.lukianbat.urbanist.urbanist_guide.feature.map.domain.model.Point
import com.lukianbat.urbanist.urbanist_guide.feature.map.domain.model.RouteModel
import io.reactivex.Single

interface MapRepository {
    fun getRoute(points: List<Point>): Single<RouteModel>
}