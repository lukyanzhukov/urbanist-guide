package com.lukianbat.urbanist.urbanist_guide.feature.list.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class PlacesRes(

    @SerializedName("code") val code: Int,
    @SerializedName("description") val description: String,
    @SerializedName("body") val place: ArrayList<Place>
)

@Parcelize
data class Place(

    @SerializedName("originId") val originId: Long,
    @SerializedName("name") val name: String,
    @SerializedName("weight") val weight: Double,
    @SerializedName("type") val type: String,
    @SerializedName("subtype") val subtype: String,
    @SerializedName("localizedSubtype") val localizedSubtype: String,
    @SerializedName("importance") val importance: String,
    @SerializedName("visibility") val visibility: String,
    @SerializedName("importanceScore") val importanceScore: Long,
    @SerializedName("coordinates") val coordinates: List<List<Double>>,
    @SerializedName("centroid") val centroid: List<Double>,
    @SerializedName("links") val links: List<String>,
    @SerializedName("geometryType") val geometryType: String
) : Parcelable