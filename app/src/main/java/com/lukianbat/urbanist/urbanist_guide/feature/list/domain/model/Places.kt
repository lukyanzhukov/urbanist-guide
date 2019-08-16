package com.lukianbat.urbanist.urbanist_guide.feature.list.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.lukianbat.urbanist.urbanist_guide.feature.list.data.CachePlacesConverter
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
@TypeConverters(CachePlacesConverter::class)
data class Places(
    val places: ArrayList<Place>?
) : Parcelable {
    @PrimaryKey
    var id: Long = 1
}

@Parcelize
class Place(

    val name: String,
    val type: String,
    val importanceScore: Long,
    val lat: Double,
    val lng: Double
) : Parcelable