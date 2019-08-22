package com.lukianbat.urbanist.guide.сore.domain.repository.preference

import com.lukianbat.urbanist.guide.сore.domain.City

interface PreferenceRepository {
    fun getCityName(): String?
    fun getCity(): City
    fun setCityName(city: String, callback: StatusCallback)
}