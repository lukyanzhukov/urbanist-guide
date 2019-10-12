package com.lukianbat.urbanist.guide.feature.map.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukianbat.urbanist.guide.feature.list.domain.model.Place
import com.lukianbat.urbanist.guide.feature.list.data.repository.MapRepository
import com.lukianbat.urbanist.guide.feature.map.domain.model.Point
import com.lukianbat.urbanist.guide.сore.utils.decodePolyline
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MapsViewModel @Inject constructor(
    private val repository: MapRepository
) : ViewModel() {
    val disposables = CompositeDisposable()
    val liveDataRoute = MutableLiveData<ArrayList<Point>>()

    fun getRoute(placeList: ArrayList<Place>) {
        val points = arrayListOf<Point>()
        placeList.forEach {
            points.add(Point(it.lat, it.lng))
        }
        repository
            .getRoute(points)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                val points = decodePolyline(result.points)
                liveDataRoute.value = points
            }, { error ->
                Log.i("TAG", error.message.toString())
            }).addTo(disposables)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}