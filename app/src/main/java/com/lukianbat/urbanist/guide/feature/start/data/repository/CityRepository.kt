package com.lukianbat.urbanist.guide.feature.start.data.repository

import com.lukianbat.urbanist.guide.BuildConfig
import com.lukianbat.urbanist.guide.feature.start.data.datasource.CityDataSource
import javax.inject.Inject

interface CityRepository {
    fun getCityName(): String
    fun setCityName(city: String, callback: StatusCallback)
}

class CityRepositoryImpl @Inject constructor(private val dataSource: CityDataSource) :
    CityRepository {

    override fun getCityName(): String = dataSource.getCityName().toString()

    override fun setCityName(city: String, callback: StatusCallback) {
        val citiesNames: Array<String> = BuildConfig.CITIES_ARRAY
        if (citiesNames.contains(city)) {
            dataSource.setCityName(city)
            callback.onSuccess()
        } else
            callback.onError()
    }

    companion object {
        const val CITY_KEY = "city"
    }

}