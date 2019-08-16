package com.lukianbat.urbanist.urbanist_guide.feature.map.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.lukianbat.urbanist.urbanist_guide.feature.map.data.CacheRouteConverter

@Entity
@TypeConverters(CacheRouteConverter::class)
data class RouteModel(

    val distance: Double,
    val time: Int,
    val points: String,
    val snappedWaypoints: String
) {
    @PrimaryKey
    var id: Long = 1
}