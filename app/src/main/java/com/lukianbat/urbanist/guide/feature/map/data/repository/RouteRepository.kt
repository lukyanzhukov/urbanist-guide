package com.lukianbat.urbanist.guide.feature.map.data.repository

import android.annotation.SuppressLint
import android.util.Log
import com.lukianbat.urbanist.guide.feature.map.data.datasource.api.RouteApiDataSource
import com.lukianbat.urbanist.guide.feature.map.data.datasource.cache.CacheRouteDataSource
import com.lukianbat.urbanist.guide.feature.map.domain.model.Point
import com.lukianbat.urbanist.guide.feature.map.domain.model.RouteModel
import com.lukianbat.urbanist.guide.сore.App
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface RouteRepository {
    fun getRoute(points: List<Point>): Single<RouteModel>
}

class RouteRepositoryImpl @Inject constructor(
    private val cacheRouteDataSource: CacheRouteDataSource,
    private val apiRouteDataSource: RouteApiDataSource
) : RouteRepository {

    @SuppressLint("CheckResult")
    override fun getRoute(points: List<Point>): Single<RouteModel> {
        val pointsList = arrayListOf<String>()
        points.forEach {
            pointsList.add(it.lat.toString() + "," + it.lng.toString())
        }
        val route = apiRouteDataSource.getRoute(pointsList)
        return if (App.hasNetwork()) {
            route.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    cacheRouteDataSource.addRoute(result)
                        .subscribeOn(Schedulers.io())
                        .subscribe {}
                }, { error ->
                    Log.i("TAG", error.message.toString())
                })
            route
        } else {
            cacheRouteDataSource.getRouteById(1)
        }
    }

}