package com.lukianbat.urbanist.urbanist_guide.сore.domain

import android.content.Context
import android.content.Context.MODE_PRIVATE
import javax.inject.Inject

class PreferenceRepository @Inject constructor(context: Context) {
    private val preferences = context.getSharedPreferences("preferences", MODE_PRIVATE)

    fun isLaunched(): Boolean = preferences.getBoolean(FIRST_LAUNCH_KEY, false)

    fun launched() {
        preferences.edit().putBoolean(FIRST_LAUNCH_KEY, true).apply()
    }

    fun getCity() : String? = preferences.getString(CITY_KEY, null)

    fun setCity(city: String) {
        preferences.edit().putString(CITY_KEY,city).apply()
    }

    companion object {
        const val FIRST_LAUNCH_KEY = "launched"
        const val CITY_KEY = "city"
    }
}