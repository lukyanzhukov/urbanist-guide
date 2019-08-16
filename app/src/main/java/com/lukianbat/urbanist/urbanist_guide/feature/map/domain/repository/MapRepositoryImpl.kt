package com.lukianbat.urbanist.urbanist_guide.feature.map.domain.repository

import android.annotation.SuppressLint
import android.util.Log
import com.lukianbat.urbanist.urbanist_guide.feature.list.domain.repository.MapRepository
import com.lukianbat.urbanist.urbanist_guide.feature.map.data.CacheRouteDao
import com.lukianbat.urbanist.urbanist_guide.feature.map.data.MapApi
import com.lukianbat.urbanist.urbanist_guide.feature.map.domain.model.Point
import com.lukianbat.urbanist.urbanist_guide.feature.map.domain.model.RouteModel
import com.lukianbat.urbanist.urbanist_guide.сore.App
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MapRepositoryImpl(
    private val api: MapApi,
    private val dao: CacheRouteDao
) : MapRepository {
    @SuppressLint("CheckResult")
    override fun getRoute(points: List<Point>): Single<RouteModel> {
        val pointsList = arrayListOf<String>()
        points.forEach {
            pointsList.add(it.lat.toString() + "," + it.lng.toString())
        }
        val route = api.getRoute(pointsList)
        return if (App.hasNetwork()) {
            route.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    dao.addRoute(result)
                        .subscribeOn(Schedulers.io())
                        .subscribe {}
                }, { error ->
                    Log.i("TAG", error.message.toString())
                })
            route
        } else {
            dao.getRouteById(1)
        }
    }
}