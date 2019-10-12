package com.lukianbat.urbanist.guide.feature.list.data.datasource.api

import com.lukianbat.urbanist.guide.feature.list.domain.model.Places
import io.reactivex.Single
import javax.inject.Inject

interface PlacesListApiDataSource {
    fun getPlaces(lat: Double, lng: Double): Single<Places>
}

class PlacesListApiDataSourceImpl @Inject constructor(private val api: PlacesListApi) :
    PlacesListApiDataSource {
    override fun getPlaces(lat: Double, lng: Double): Single<Places> = api.getPlaces(lat, lng)

}