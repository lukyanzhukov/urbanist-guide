package com.lukianbat.urbanist.urbanist_guide.feature.start

import androidx.lifecycle.MutableLiveData
import com.lukianbat.urbanist.urbanist_guide.R
import com.lukianbat.urbanist.urbanist_guide.сore.domain.repository.preference.PreferenceRepositoryImpl
import com.lukianbat.urbanist.urbanist_guide.сore.domain.repository.preference.StatusCallback
import com.lukianbat.urbanist.urbanist_guide.сore.presentation.BaseViewModel
import javax.inject.Inject

class StartViewModule @Inject constructor(
    private val preferenceRepository: PreferenceRepositoryImpl
) : BaseViewModel() {
    lateinit var eventsListener: EventsListener
    val city = MutableLiveData<String>()

    init {
        city.value = preferenceRepository.getCityName()
        city.observeForever {
            if (it.contains(' ') || it.contains('\n')) {
                var res = ""
                for (i in it) {
                    if (i != ' ' && i != '\n') {
                        res += i
                    }
                }
                city.value = res
            }
        }
    }

    fun onOkClick() {
        if (city.value.isNullOrEmpty()) {
            eventsListener.showMessage(R.string.null_city_message)
            return
        }
        preferenceRepository.setCityName(city.value.toString(), object : StatusCallback {

            override fun onSuccess() {
                eventsListener.routeToPlaceList()
            }

            override fun onError() {
                eventsListener.showMessage(R.string.unknown_city_message)
            }

        })
    }

    fun setEventListener(eventsListener: EventsListener) {
        this.eventsListener = eventsListener
    }

    interface EventsListener {
        fun showMessage(messageId: Int)
        fun routeToPlaceList()
    }
}