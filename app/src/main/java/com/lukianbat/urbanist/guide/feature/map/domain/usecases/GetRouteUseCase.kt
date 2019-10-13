package com.lukianbat.urbanist.guide.feature.map.domain.usecases

import com.lukianbat.urbanist.guide.feature.map.data.repository.RouteRepository
import com.lukianbat.urbanist.guide.feature.map.domain.model.Point
import com.lukianbat.urbanist.guide.feature.map.domain.model.RouteModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface GetRouteUseCase {
    fun getRoute(points: List<Point>): Single<RouteModel>
}

class GetRouteUseCaseImpl @Inject constructor(private val repository: RouteRepository) :
    GetRouteUseCase {

    override fun getRoute(points: List<Point>): Single<RouteModel> =
        repository.getRoute(points)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}