package com.lukianbat.urbanist.urbanist_guide.сore.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.lukianbat.urbanist.urbanist_guide.feature.map.domain.model.Point

fun decodePolyline(encoded: String): ArrayList<Point> {
    val poly = arrayListOf<Point>()
    var index = 0
    val len = encoded.length
    var lat = 0
    var lng = 0
    while (index < len) {
        // latitude
        var b: Int
        var shift = 0
        var result = 0
        do {
            b = encoded[index++].toInt() - 63
            result = result or (b and 0x1f shl shift)
            shift += 5
        } while (b >= 0x20)
        val deltaLatitude = if (result and 1 != 0) (result shr 1).inv() else result shr 1
        lat += deltaLatitude

        // longitute
        shift = 0
        result = 0
        do {
            b = encoded[index++].toInt() - 63
            result = result or (b and 0x1f shl shift)
            shift += 5
        } while (b >= 0x20)
        val deltaLongitude = if (result and 1 != 0) (result shr 1).inv() else result shr 1
        lng += deltaLongitude

        poly.add(Point(lat.toDouble() / 1e5, lng.toDouble() / 1e5))
    }
    return poly
}

fun Activity.bitmapDescriptorFromVector(vectorResId: Int): BitmapDescriptor? {
    return ContextCompat.getDrawable(baseContext, vectorResId)?.run {
        setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
        draw(Canvas(bitmap))
        BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}