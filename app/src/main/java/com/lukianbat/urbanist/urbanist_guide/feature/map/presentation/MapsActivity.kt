package com.lukianbat.urbanist.urbanist_guide.feature.map.presentation

import android.os.Bundle
import com.lukianbat.urbanist.urbanist_guide.R
import com.lukianbat.urbanist.urbanist_guide.databinding.ActivityMapsBinding
import com.lukianbat.urbanist.urbanist_guide.feature.list.domain.model.Body
import com.lukianbat.urbanist.urbanist_guide.сore.presentation.BaseActivity
import javax.inject.Inject

class MapsActivity : BaseActivity<ActivityMapsBinding>() {

    @Inject
    lateinit var viewModel: MapsViewModel
    lateinit var placesList: ArrayList<Body>
    override val layoutId: Int = R.layout.activity_maps
    override fun initBinding() {
        requireBinding().viewModel = viewModel
    }

    override fun initViewModel(state: Bundle?) {
        placesList = intent.getParcelableArrayListExtra<Body>("checkPlaces") as ArrayList
        viewModel.getRoute(placesList)
    }


//    override fun onMapReady(googleMap: GoogleMap) {
////        mMap = googleMap
////        placesList.forEach {
////            mMap.addMarker(MarkerOptions().position(LatLng(it.centroid[0], it.centroid[1])).title(it.name))
////        }
////        mMap.moveCamera(
////            CameraUpdateFactory.newLatLng(
////                LatLng(
////                    placesList.first().centroid[0],
////                    placesList.first().centroid[1]
////                )
////            )
////        )
////        viewModel.getRoute(placesList)
//
//    }
}
