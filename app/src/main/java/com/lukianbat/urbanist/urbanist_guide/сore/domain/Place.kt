package com.lukianbat.urbanist.urbanist_guide.сore.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Place(val name: String?, val description: String?, val lat: Float, val lng: Float) : Parcelable
