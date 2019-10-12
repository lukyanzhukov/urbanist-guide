package com.lukianbat.urbanist.guide.feature.start.presentation

import androidx.lifecycle.MutableLiveData
import com.lukianbat.urbanist.guide.R
import com.lukianbat.urbanist.guide.feature.start.data.repository.StatusCallback
import com.lukianbat.urbanist.guide.feature.start.domain.usecases.GetCityNameUseCase
import com.lukianbat.urbanist.guide.feature.start.domain.usecases.SetCityNameUseCase
import com.lukianbat.urbanist.guide.сore.presentation.eventsdispatcher.EventsDispatcher
import com.lukianbat.urbanist.guide.сore.presentation.eventsdispatcher.EventsDispatcherOwner
import com.lukianbat.urbanist.guide.сore.presentation.viewmodel.BaseViewModel
import javax.inject.Inject

class StartViewModule @Inject constructor(
    private val getCityNameUseCase: GetCityNameUseCase,
    private val setCityNameUseCase: SetCityNameUseCase
) : BaseViewModel(), EventsDispatcherOwner<StartViewModule.EventsListener> {
    override val eventsDispatcher: EventsDispatcher<EventsListener> = EventsDispatcher()
    val city = MutableLiveData<String>()

    init {
        city.value = getCityNameUseCase.cityName
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
            eventsDispatcher.dispatchEvent { showMessage(R.string.null_city_message) }
            return
        }
        city.value =
            city.value?.first()?.toUpperCase()?.plus(city.value?.substring(1).toString())

        setCityNameUseCase.setCityName(city.value.toString(), object :
            StatusCallback {

            override fun onSuccess() {
                eventsDispatcher.dispatchEvent { routeToPlaceList() }
            }

            override fun onError() {
                eventsDispatcher.dispatchEvent { showMessage(R.string.unknown_city_message) }
            }

        })
    }

    interface EventsListener {
        fun showMessage(messageId: Int)
        fun routeToPlaceList()
    }
}