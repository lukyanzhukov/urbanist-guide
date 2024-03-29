package com.lukianbat.urbanist.guide.feature.map.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.lukianbat.urbanist.guide.R
import com.lukianbat.urbanist.guide.feature.list.domain.model.Place
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import com.lukianbat.urbanist.guide.сore.utils.bitmapDescriptorFromVector


class MapsActivity : DaggerAppCompatActivity(), OnMapReadyCallback {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: MapsViewModel
    private lateinit var placesList: ArrayList<Place>
    private lateinit var line: PolylineOptions
    private var mMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        initViewModel()
        placesList = intent.getParcelableArrayListExtra<Place>("checkPlaces") as ArrayList
        if (savedInstanceState != null) {
            line = savedInstanceState.getParcelable<PolylineOptions>("line") as PolylineOptions
        } else {
            viewModel.getRoute(placesList)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MapsViewModel::class.java)
        viewModel.liveDataRoute.observe(this, Observer { points ->
            line = PolylineOptions()
            points.forEach {
                line.add(LatLng(it.lat, it.lng))
            }
            setUpMapIfNeeded()
        })
    }

    private fun setupMap(googleMap: GoogleMap) {
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    placesList.first().lat,
                    placesList.first().lng
                ), 13.toFloat()
            )
        )
        googleMap.setMinZoomPreference(10.5f)
        googleMap.setMaxZoomPreference(25.0f)
        googleMap.isBuildingsEnabled = true
    }

    private fun setUpMapIfNeeded() {
        if (mMap == null) {
            (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment)
                .getMapAsync(this)
        }
    }

    private fun drawMarkers(googleMap: GoogleMap) {

        val start = placesList.first()
        val finish = placesList.last()
        googleMap.addMarker(
            MarkerOptions().position(LatLng(start.lat, start.lng)).title(start.name)
                .icon(bitmapDescriptorFromVector(R.drawable.ic_start_marker))
        )
        googleMap.addMarker(
            MarkerOptions().position(LatLng(finish.lat, finish.lng)).title(finish.name)
                .icon(bitmapDescriptorFromVector(R.drawable.ic_finish_marker))
        )
        val markers = arrayListOf<Place>()
        markers.addAll(placesList)
        markers.let {
            it.remove(start)
            it.remove(finish)
        }
        markers.forEach {
            googleMap.addMarker(
                MarkerOptions().position(LatLng(it.lat, it.lng)).title(it.name)
                    .icon(bitmapDescriptorFromVector(R.drawable.ic_place_marker))
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("line", line)
    }

    private fun drawRoute(googleMap: GoogleMap) {
        line.width(10f)?.color(
            Color
                .parseColor(
                    resources
                        .getString(R.string.colorMap)
                )
        )
        googleMap.addPolyline(line)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposables.dispose()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        setupMap(googleMap)
        drawMarkers(googleMap)
        drawRoute(googleMap)
        mMap = googleMap
    }
}
