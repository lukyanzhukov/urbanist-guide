package com.lukianbat.urbanist.guide.feature.list.data.datasource.api

import com.google.gson.JsonElement
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.lukianbat.urbanist.guide.feature.list.domain.model.Place
import com.lukianbat.urbanist.guide.feature.list.domain.model.Places
import java.lang.reflect.Type


class PlacesDeserializer : JsonDeserializer<Places> {

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Places {
        val places = arrayListOf<Place>()
        val jsonList = json.asJsonObject.get("body").asJsonArray
        jsonList.forEach { jsonPlace ->
            jsonPlace.asJsonObject.let {
                val name = (it.get("name") ?: "").toString()
                val type = (it.get("localizedSubtype") ?: "").toString()
                val importanceScore = it.get("importanceScore").asLong
                val lat = it.get("centroid").asJsonArray.get(0).asDouble
                val lng = it.get("centroid").asJsonArray.get(1).asDouble
                places.add(Place(name, type, importanceScore, lat, lng))
            }
        }
        return Places(places)
    }
}