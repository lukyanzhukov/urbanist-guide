package com.lukianbat.urbanist.guide.feature.start

import androidx.lifecycle.MutableLiveData
import com.lukianbat.urbanist.guide.R
import com.lukianbat.urbanist.guide.сore.domain.repository.preference.PreferenceRepositoryImpl
import com.lukianbat.urbanist.guide.сore.domain.repository.preference.StatusCallback
import com.lukianbat.urbanist.guide.сore.presentation.BaseViewModel
import javax.inject.Inject

class StartViewModule @Inject constructor(
    private val preferenceRepository: PreferenceRepositoryImpl
) : BaseViewModel() {
    lateinit var eventsListener: EventsListener
    val city = MutableLiveData<String>()

    init {
        city.value = preferenceRepository.getCityName()
        city.observeForever {
            if (it.isNullOrEmpty().not()) {
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
    }

    fun onOkClick() {
        if (city.value.isNullOrEmpty()) {
            eventsListener.showMessage(R.string.null_city_message)
            return
        }
        city.value = city.value?.first()?.toUpperCase()?.plus(city.value?.substring(1).toString())

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