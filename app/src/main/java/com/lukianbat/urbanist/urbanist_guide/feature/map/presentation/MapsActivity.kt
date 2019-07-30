package com.lukianbat.urbanist.urbanist_guide.feature.map.presentation

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.lukianbat.urbanist.urbanist_guide.R
import com.lukianbat.urbanist.urbanist_guide.feature.list.domain.model.Body
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.PolylineOptions
import com.lukianbat.urbanist.urbanist_guide.feature.map.domain.model.Point


class MapsActivity : DaggerAppCompatActivity(), OnMapReadyCallback {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: MapsViewModel
    lateinit var placesList: ArrayList<Body>
    lateinit var mMap: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        initViewModel()
        placesList = intent.getParcelableArrayListExtra<Body>("checkPlaces") as ArrayList
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MapsViewModel::class.java)
        viewModel.liveDataRoute.observe(this, Observer { points ->
            drawRoute(points)
        })
    }

    private fun drawRoute(points: ArrayList<Point>) {
        val line = PolylineOptions()
        val latLngBuilder = LatLngBounds.Builder()
        points.forEach {
            line.add(LatLng(it.lat, it.lng))
            latLngBuilder.include(LatLng(it.lat, it.lng))
        }
        line.width(8f).color(R.color.colorPrimary)
        mMap.addPolyline(line)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.setMinZoomPreference(10.0f)
        googleMap.setMaxZoomPreference(25.0f)
        googleMap.isBuildingsEnabled = true
        placesList.forEach {
            googleMap.addMarker(MarkerOptions().position(LatLng(it.centroid[0], it.centroid[1])).title(it.name))
        }
        googleMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    placesList.first().centroid[0],
                    placesList.first().centroid[1]
                ), 18.toFloat()
            )
        )
        mMap = googleMap
        viewModel.getRoute(placesList)
    }
}
