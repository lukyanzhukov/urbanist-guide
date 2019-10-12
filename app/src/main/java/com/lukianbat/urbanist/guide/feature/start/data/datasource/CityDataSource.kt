package com.lukianbat.urbanist.guide.feature.start.data.datasource

import android.content.SharedPreferences
import javax.inject.Inject

interface CityDataSource {
    fun getCityName(): String?
    fun setCityName(city: String)
}

class CityDataSourceImpl @Inject constructor(private val preferences: SharedPreferences) :
    CityDataSource {
    override fun getCityName(): String? =
        preferences.getString(CITY_KEY, null)

    override fun setCityName(city: String) {
        preferences.edit().putString(CITY_KEY, city).apply()
    }

    companion object {
        const val CITY_KEY = "city"
    }
}