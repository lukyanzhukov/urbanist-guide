package com.lukianbat.urbanist.urbanist_guide.feature.start

import androidx.lifecycle.MutableLiveData
import com.lukianbat.urbanist.urbanist_guide.сore.domain.repository.preference.PreferenceRepositoryImpl
import com.lukianbat.urbanist.urbanist_guide.сore.presentation.BaseViewModel
import javax.inject.Inject

class StartViewModule @Inject constructor(
    private val preferenceRepository: PreferenceRepositoryImpl
) : BaseViewModel() {
    lateinit var eventsListener: EventsListener
    val city = MutableLiveData<String>()

    init {
        city.value = preferenceRepository.getCityName()
    }

    fun onOkClick() {
        if (city.value.isNullOrEmpty()) {
            eventsListener.showMessage("Введите город!")
            return
        }
        preferenceRepository.setCityName(city.value.toString())
        eventsListener.routeToPlaceList()
    }

    fun setEventListener(eventsListener: EventsListener) {
        this.eventsListener = eventsListener
    }

    interface EventsListener {
        fun showMessage(message: String)
        fun routeToPlaceList()
    }
}