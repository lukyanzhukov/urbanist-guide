package com.lukianbat.urbanist.guide.feature.list.data.repository

import com.lukianbat.urbanist.guide.BuildConfig
import com.lukianbat.urbanist.guide.feature.list.data.datasource.api.PlacesListApiDataSource
import com.lukianbat.urbanist.guide.feature.list.data.datasource.db.PlacesListCacheDataSource
import com.lukianbat.urbanist.guide.feature.list.domain.model.City
import com.lukianbat.urbanist.guide.feature.list.domain.model.Places
import com.lukianbat.urbanist.guide.feature.start.data.datasource.CityDataSource

import com.lukianbat.urbanist.guide.сore.App
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

interface PlacesListRepository {
    fun getPlaces(): Single<Places>
    fun setPlaces(places: Places): Completable
}

class PlacesListRepositoryImpl @Inject constructor(
    private val cacheDataSource: PlacesListCacheDataSource,
    private val apiDataSource: PlacesListApiDataSource,
    private val cityDataSource: CityDataSource
) :
    PlacesListRepository {

    override fun setPlaces(places: Places): Completable = cacheDataSource.addPlaces(places)

    override fun getPlaces(): Single<Places> {
        return if (App.hasNetwork()) {
            val city = getCity()
            apiDataSource.getPlaces(city.lat, city.lng)
        } else {
            cacheDataSource.getPlace()
        }
    }

    private fun getCity(): City {
        val city = cityDataSource.getCityName()
        val citiesNames: Array<String> = BuildConfig.CITIES_ARRAY
        val lat = arrayListOf<Double>()
        val lng = arrayListOf<Double>()
        val latString = BuildConfig.LAT_ARRAY
        val lngString = BuildConfig.LNG_ARRAY
        latString.forEach {
            lat.add(it.toDouble())
        }
        lngString.forEach {
            lng.add(it.toDouble())
        }
        val index = citiesNames.indexOf(city)
        return City(
            city,
            lat[index],
            lng[index]
        )
    }
}