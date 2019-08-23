package com.lukianbat.urbanist.guide.feature.list.domain.repository

import com.lukianbat.urbanist.guide.feature.list.domain.model.Places
import io.reactivex.Completable
import io.reactivex.Single

interface PlaceListRepository {
    fun getPlaces(lat: Double, lng: Double): Single<Places>
    fun setCheckedPlaces(places: Places): Completable
}