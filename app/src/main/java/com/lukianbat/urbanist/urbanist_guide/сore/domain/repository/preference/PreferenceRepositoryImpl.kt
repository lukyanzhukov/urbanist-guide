package com.lukianbat.urbanist.urbanist_guide.сore.domain.repository.preference

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.lukianbat.urbanist.urbanist_guide.R
import com.lukianbat.urbanist.urbanist_guide.сore.domain.City
import javax.inject.Inject

class PreferenceRepositoryImpl @Inject constructor(val context: Context) : PreferenceRepository {
    private val preferences = context.getSharedPreferences("preferences", MODE_PRIVATE)

    override fun getCityName(): String? = preferences.getString(CITY_KEY, null)
    override fun getCity(): City {
        val city = getCityName()
        val names: Array<String> = context.resources.getStringArray(R.array.cities)
        val lat = arrayListOf<Double>()
        val lng = arrayListOf<Double>()
        val latString = context.resources.getStringArray(R.array.cities_lat)
        val lngString = context.resources.getStringArray(R.array.cities_lng)
        latString.forEach {
            lat.add(it.toDouble())
        }
        lngString.forEach {
            lng.add(it.toDouble())
        }
        val index = names.indexOf(city)
        return City(city, lat[index], lng[index])
    }

    override fun setCityName(city: String) {
        preferences.edit().putString(CITY_KEY, city).apply()
    }

    companion object {
        const val CITY_KEY = "city"
    }

}