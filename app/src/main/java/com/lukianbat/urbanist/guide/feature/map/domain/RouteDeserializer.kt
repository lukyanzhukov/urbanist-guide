package com.lukianbat.urbanist.guide.feature.map.domain

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.lukianbat.urbanist.guide.feature.map.domain.model.RouteModel
import java.lang.reflect.Type

class RouteDeserializer : JsonDeserializer<RouteModel> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): RouteModel {
        val path = json.asJsonObject.get("paths").asJsonArray
        path.first().asJsonObject.let {
            val distance = it.get("distance").asDouble
            val time = it.get("time").asInt
            val points = it.get("points").asString
            val snappedWaypoints = it.get("snapped_waypoints").asString
            return RouteModel(distance, time, points, snappedWaypoints)
        }
    }
}