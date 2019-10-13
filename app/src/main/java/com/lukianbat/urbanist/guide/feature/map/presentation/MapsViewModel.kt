package com.lukianbat.urbanist.guide.feature.map.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukianbat.urbanist.guide.feature.list.domain.model.Place
import com.lukianbat.urbanist.guide.feature.map.domain.model.Point
import com.lukianbat.urbanist.guide.feature.map.domain.usecases.GetRouteUseCase
import com.lukianbat.urbanist.guide.сore.utils.decodePolyline
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class MapsViewModel @Inject constructor(
    private val getRouteUseCase: GetRouteUseCase
) : ViewModel() {
    val disposables = CompositeDisposable()
    val liveDataRoute = MutableLiveData<ArrayList<Point>>()

    fun getRoute(placeList: ArrayList<Place>) {
        val points = arrayListOf<Point>()
        placeList.forEach {
            points.add(Point(it.lat, it.lng))
        }
        getRouteUseCase
            .getRoute(points)
            .subscribe({ result ->
                val points = decodePolyline(result.points)
                liveDataRoute.value = points
            }, {}).addTo(disposables)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}