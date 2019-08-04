package com.lukianbat.urbanist.urbanist_guide.feature.list.domain.repository

import com.lukianbat.urbanist.urbanist_guide.feature.list.data.PlaceListApi
import com.lukianbat.urbanist.urbanist_guide.feature.list.domain.model.PlacesRes
import io.reactivex.Single

class PlaceListRepositoryImpl(private val placeListApi: PlaceListApi) : PlaceListRepository {

    override fun getPlaces(lat: Double, lng: Double): Single<PlacesRes> = placeListApi.getPlaces(lat, lng)

}