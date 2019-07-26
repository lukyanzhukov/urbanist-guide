package com.lukianbat.urbanist.urbanist_guide.feature.list.domain.repository

import com.lukianbat.urbanist.urbanist_guide.feature.list.domain.model.PlacesRes
import io.reactivex.Single

interface PlaceListRepository {
    fun getPlaces(lat: Double, lng: Double): Single<PlacesRes>
}