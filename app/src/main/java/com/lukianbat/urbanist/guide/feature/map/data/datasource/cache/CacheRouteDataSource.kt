package com.lukianbat.urbanist.guide.feature.map.data.datasource.cache

import com.lukianbat.urbanist.guide.feature.map.domain.model.RouteModel
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

interface CacheRouteDataSource {

    fun addRoute(route: RouteModel): Completable
    fun getRouteById(id: Long): Single<RouteModel>

}

class CacheRouteDataSourceImpl @Inject constructor(private val dao: CacheRouteDao) :
    CacheRouteDataSource {

    override fun getRouteById(id: Long): Single<RouteModel> = dao.getRouteById(id)

    override fun addRoute(route: RouteModel): Completable = dao.addRoute(route)

}