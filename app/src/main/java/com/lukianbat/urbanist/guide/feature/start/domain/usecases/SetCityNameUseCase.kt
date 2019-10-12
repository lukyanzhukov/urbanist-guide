package com.lukianbat.urbanist.guide.feature.start.domain.usecases

import com.lukianbat.urbanist.guide.feature.start.data.repository.CityRepository
import com.lukianbat.urbanist.guide.feature.start.data.repository.StatusCallback
import javax.inject.Inject

interface SetCityNameUseCase {
    fun setCityName(name: String, callback: StatusCallback)
}

class SetCityNameUseCaseImpl @Inject constructor(private val repository: CityRepository) :
    SetCityNameUseCase {

    override fun setCityName(name: String, callback: StatusCallback) {
        repository.setCityName(name, callback)
    }

}