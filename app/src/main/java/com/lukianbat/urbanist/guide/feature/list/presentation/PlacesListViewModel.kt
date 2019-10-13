package com.lukianbat.urbanist.guide.feature.list.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.lukianbat.urbanist.guide.R
import com.lukianbat.urbanist.guide.feature.list.domain.model.Place
import com.lukianbat.urbanist.guide.feature.list.domain.model.Places
import com.lukianbat.urbanist.guide.feature.list.domain.usecases.GetPlacesUseCase
import com.lukianbat.urbanist.guide.feature.list.domain.usecases.SetPlacesUseCase
import com.lukianbat.urbanist.guide.сore.presentation.eventsdispatcher.EventsDispatcher
import com.lukianbat.urbanist.guide.сore.presentation.eventsdispatcher.EventsDispatcherOwner
import com.lukianbat.urbanist.guide.сore.presentation.viewmodel.BaseViewModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PlacesListViewModel @Inject constructor(
    private val getPlacesUseCase: GetPlacesUseCase,
    private val setPlacesUseCase: SetPlacesUseCase
) : BaseViewModel(), EventsDispatcherOwner<PlacesListViewModel.EventsListener> {
    override val eventsDispatcher: EventsDispatcher<EventsListener> = EventsDispatcher()
    val liveData = MutableLiveData<ArrayList<Place>>()

    init {
        getPlaces()
    }

    @SuppressLint("CheckResult")
    fun getPlaces() {
        getPlacesUseCase.getPlaces()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({ result ->
                liveData.value = result.places
            }, {
                eventsDispatcher.dispatchEvent { showMessage(R.string.error_message) }
            }).addTo(compositeDisposable)

    }

    interface EventsListener {
        fun routeToCacheMap()
        fun routeToMap()
        fun showMessage(messageId: Int)
    }

    fun setCash(places: Places): Completable = setPlacesUseCase.setPlaces(places)

    fun onClick() {
        eventsDispatcher.dispatchEvent { routeToMap() }
    }
}