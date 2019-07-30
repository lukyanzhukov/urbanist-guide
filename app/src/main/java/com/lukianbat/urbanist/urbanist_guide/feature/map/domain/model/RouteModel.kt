package com.lukianbat.urbanist.urbanist_guide.feature.map.domain.model

import com.google.gson.annotations.SerializedName

data class RouteModel(

    @SerializedName("hints") val hints: Hints,
    @SerializedName("paths") val paths: List<Paths>,
    @SerializedName("info") val info: Info
)

data class Paths(

    @SerializedName("distance") val distance: Double,
    @SerializedName("weight") val weight: Double,
    @SerializedName("time") val time: Int,
    @SerializedName("transfers") val transfers: Int,
    @SerializedName("points_encoded") val pointsEncoded: Boolean,
    @SerializedName("bbox") val bbox: List<Double>,
    @SerializedName("points") val points: String,
    @SerializedName("legs") val legs: List<String>,
    @SerializedName("details") val details: Details,
    @SerializedName("ascend") val ascend: Double,
    @SerializedName("descend") val descend: Double,
    @SerializedName("snapped_waypoints") val snappedWaypoints: String,
    @SerializedName("points_order") val points_order: List<Int>
)

data class Details(val any: Any?)

data class Info(

    @SerializedName("copyrights") val copyrights: List<String>,
    @SerializedName("took") val took: Int
)

data class Hints(

    @SerializedName("visited_nodes.average") val visitedNodesAverage: Double,
    @SerializedName("visited_nodes.sum") val visitedNodesSum: Int
)