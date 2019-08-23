package com.lukianbat.urbanist.guide.feature.map.data

import androidx.room.*
import com.lukianbat.urbanist.guide.feature.map.domain.model.RouteModel
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CacheRouteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRoute(route: RouteModel): Completable

    @Query("SELECT * FROM routemodel WHERE id = :id")
    fun getRouteById(id: Long): Single<RouteModel> }
