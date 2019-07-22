package com.lukianbat.urbanist.urbanist_guide.feature.start

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.lukianbat.urbanist.urbanist_guide.сore.domain.PreferenceRepository
import com.lukianbat.urbanist.urbanist_guide.сore.presentation.BaseViewModel
import javax.inject.Inject

class StartViewModule @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) : BaseViewModel() {
    lateinit var eventsListener: EventsListener
    val city = MutableLiveData<String>()

    init {
        city.value = preferenceRepository.getCity()
    }

    fun onOkClick() {
        if (city.value.isNullOrEmpty()) {
            eventsListener.showMessage("Введите город!")
            return
        }
        preferenceRepository.setCity(city.value!!)
        eventsListener.routeToList()
    }

    fun setEventListener(eventsListener: EventsListener) {
        this.eventsListener = eventsListener
    }

    interface EventsListener {
        fun showMessage(message: String)
        fun routeToList()
    }
}