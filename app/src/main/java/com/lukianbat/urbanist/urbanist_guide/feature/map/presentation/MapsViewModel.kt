package com.lukianbat.urbanist.urbanist_guide.feature.map.presentation

import android.util.Log
import com.lukianbat.urbanist.urbanist_guide.feature.list.domain.model.Body
import com.lukianbat.urbanist.urbanist_guide.feature.list.domain.repository.MapRepository
import com.lukianbat.urbanist.urbanist_guide.feature.map.domain.model.Point
import com.lukianbat.urbanist.urbanist_guide.сore.presentation.BaseViewModel
import com.lukianbat.urbanist.urbanist_guide.сore.utils.decodePolyline
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MapsViewModel @Inject constructor(
    private val mapRepository: MapRepository
) : BaseViewModel() {

    fun getRoute(placeList: ArrayList<Body>) {
        val points = arrayListOf<Point>()
        placeList.forEach {
            points.add(Point(it.centroid[0], it.centroid[1]))
        }
        mapRepository
            .getRoute(points)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Log.i("TAG", result.paths.first().points)
                val points = decodePolyline(result.paths.first().points)
                Log.i("TAG", points.first().lng.toString() + "   " + points.first().lat.toString())
            }, { error ->
                Log.i("TAG", error.message.toString())
            }).addTo(disposables)
    }
}