package com.lukianbat.urbanist.guide.feature.start.domain.usecases

import com.lukianbat.urbanist.guide.feature.start.data.repository.CityRepository
import javax.inject.Inject

interface GetCityNameUseCase {
    val cityName: String
}

class GetCityNameUseCaseImpl @Inject constructor(private val repository: CityRepository) :
    GetCityNameUseCase {

    override val cityName: String
        get() = repository.getCityName()

}