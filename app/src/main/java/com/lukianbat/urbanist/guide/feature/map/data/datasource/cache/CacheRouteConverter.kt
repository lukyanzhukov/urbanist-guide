package com.lukianbat.urbanist.guide.feature.map.data.datasource.cache

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lukianbat.urbanist.guide.feature.map.domain.model.RouteModel

class CacheRouteConverter {

    companion object {
        @TypeConverter
        @JvmStatic
        fun toRouteObj(str: String): RouteModel {
            val gson = Gson()
            val type = object : TypeToken<RouteModel>() {}.type
            return gson.fromJson(str, type)
        }

        @TypeConverter
        @JvmStatic
        fun fromRouteObj(routeModel: RouteModel): String {
            val gson = Gson()
            val type = object : TypeToken<RouteModel>() {}.type
            return gson.toJson(routeModel, type)
        }
    }
}