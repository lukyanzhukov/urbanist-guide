package com.lukianbat.urbanist.urbanist_guide.feature.list.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lukianbat.urbanist.urbanist_guide.feature.list.domain.model.Place

class CachePlacesConverter {

    companion object {
        @TypeConverter
        @JvmStatic
        fun toPlacesList(str: String): ArrayList<Place> {
            val gson = Gson()
            val type = object : TypeToken<ArrayList<Place>>() {}.type
            return gson.fromJson(str, type)
        }

        @TypeConverter
        @JvmStatic
        fun fromPlacesList(places: ArrayList<Place>): String {
            val gson = Gson()
            val type = object : TypeToken<ArrayList<Place>>() {}.type
            return gson.toJson(places, type)
        }
    }
}