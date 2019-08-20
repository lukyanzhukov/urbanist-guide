package com.lukianbat.urbanist.urbanist_guide.feature.list.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.lukianbat.urbanist.urbanist_guide.R
import com.lukianbat.urbanist.urbanist_guide.feature.list.domain.model.Place
import com.lukianbat.urbanist.urbanist_guide.feature.list.domain.model.Places
import com.lukianbat.urbanist.urbanist_guide.feature.list.domain.repository.PlaceListRepository
import com.lukianbat.urbanist.urbanist_guide.сore.domain.repository.preference.PreferenceRepository
import com.lukianbat.urbanist.urbanist_guide.сore.presentation.BaseViewModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PlaceListViewModel @Inject constructor(
    private val repository: PlaceListRepository,
    private val preferenceRepository: PreferenceRepository
) : BaseViewModel() {
    lateinit var eventsListener: EventsListener
    val liveData = MutableLiveData<ArrayList<Place>>()

    init {
        getPlaces()
    }

    @SuppressLint("CheckResult")
    fun getPlaces() {
        val city = preferenceRepository.getCity()
        repository.getPlaces(city.lat, city.lng)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({ result ->
                liveData.value = result.places
            }, { error ->
                eventsListener.showMessage(R.string.error_message)
            }).addTo(disposables)

    }

    fun setEventListener(eventsListener: EventsListener) {
        this.eventsListener = eventsListener
    }

    interface EventsListener {
        fun routeToCacheMap()
        fun routeToMap()
        fun showMessage(messageId: Int)
    }

    fun setCash(places: Places): Completable = repository.setCheckedPlaces(places)

    fun onClick() {
        eventsListener.routeToMap()
    }
}