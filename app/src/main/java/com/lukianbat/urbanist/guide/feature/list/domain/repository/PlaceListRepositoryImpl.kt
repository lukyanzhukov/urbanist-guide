package com.lukianbat.urbanist.guide.feature.list.domain.repository

import com.lukianbat.urbanist.guide.feature.list.data.CachePlacesDao
import com.lukianbat.urbanist.guide.feature.list.data.PlaceListApi
import com.lukianbat.urbanist.guide.feature.list.domain.model.Places

import com.lukianbat.urbanist.guide.сore.App
import io.reactivex.Completable
import io.reactivex.Single

class PlaceListRepositoryImpl(private val placeListApi: PlaceListApi, private val dao: CachePlacesDao) :

    PlaceListRepository {

    override fun setCheckedPlaces(places: Places): Completable = dao.addPlaces(places)

    override fun getPlaces(lat: Double, lng: Double): Single<Places> {
        return if (App.hasNetwork()) {
            placeListApi.getPlaces(lat, lng)
        } else {
            dao.getPlacesById(1)
        }
    }
}