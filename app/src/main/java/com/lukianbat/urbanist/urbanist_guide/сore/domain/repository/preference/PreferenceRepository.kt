package com.lukianbat.urbanist.urbanist_guide.сore.domain.repository.preference

import com.lukianbat.urbanist.urbanist_guide.сore.domain.City

interface PreferenceRepository {
    fun getCityName(): String?
    fun getCity(): City
    fun setCityName(city: String)
}