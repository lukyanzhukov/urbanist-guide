package com.lukianbat.urbanist.guide.feature.list.domain.usecases

import com.lukianbat.urbanist.guide.feature.list.data.repository.PlacesListRepository
import com.lukianbat.urbanist.guide.feature.list.domain.model.Places
import io.reactivex.Completable
import javax.inject.Inject

interface SetPlacesUseCase {
    fun setPlaces(places: Places): Completable
}

class SetPlacesUseCaseImpl @Inject constructor(private val repository: PlacesListRepository) :
    SetPlacesUseCase {
    override fun setPlaces(places: Places): Completable = repository.setPlaces(places)

}
