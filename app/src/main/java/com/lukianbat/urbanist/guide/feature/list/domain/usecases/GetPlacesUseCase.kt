package com.lukianbat.urbanist.guide.feature.list.domain.usecases

import com.lukianbat.urbanist.guide.feature.list.data.repository.PlacesListRepository
import com.lukianbat.urbanist.guide.feature.list.domain.model.Places
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface GetPlacesUseCase {
    fun getPlaces(): Single<Places>
}

class GetPlacesUseCaseImpl @Inject constructor(private val repository: PlacesListRepository) :
    GetPlacesUseCase {

    override fun getPlaces(): Single<Places> =
        repository.getPlaces()
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())

}