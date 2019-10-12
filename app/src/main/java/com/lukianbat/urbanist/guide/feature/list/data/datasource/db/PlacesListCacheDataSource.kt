package com.lukianbat.urbanist.guide.feature.list.data.datasource.db

import com.lukianbat.urbanist.guide.feature.list.domain.model.Places
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

interface PlacesListCacheDataSource {
    fun getPlace(): Single<Places>
    fun addPlaces(places: Places): Completable
}

class PlacesListCacheDataSourceImpl @Inject constructor(private val dao: CachePlacesDao) :
    PlacesListCacheDataSource {

    override fun getPlace(): Single<Places> = dao.getPlacesById(1)

    override fun addPlaces(places: Places): Completable = dao.addPlaces(places)

}