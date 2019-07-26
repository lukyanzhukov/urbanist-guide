package com.lukianbat.urbanist.urbanist_guide.сore.domain

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.lukianbat.urbanist.urbanist_guide.R
import javax.inject.Inject

class PreferenceRepository @Inject constructor(context: Context) {
    private val preferences = context.getSharedPreferences("preferences", MODE_PRIVATE)
    private val context = context
    fun isLaunched(): Boolean = preferences.getBoolean(FIRST_LAUNCH_KEY, false)

    fun launched() {
        preferences.edit().putBoolean(FIRST_LAUNCH_KEY, true).apply()
    }

    fun isCorrectCity(city: String): Boolean {
        val names: Array<String> = context.resources.getStringArray(R.array.cities)
        return names.contains(city)
    }

    fun getCityName(): String? = preferences.getString(CITY_KEY, null)

    fun getCity(): City {
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

    fun setCityName(city: String) {
        preferences.edit().putString(CITY_KEY, city).apply()
    }

    companion object {
        const val FIRST_LAUNCH_KEY = "launched"
        const val CITY_KEY = "city"
    }

}