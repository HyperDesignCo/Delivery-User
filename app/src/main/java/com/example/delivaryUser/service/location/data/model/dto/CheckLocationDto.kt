package com.example.delivaryUser.service.location.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckLocationDto(
    @SerialName("message") val message: String,
    @SerialName("data") val data: LocationDto? = null,
)
@Serializable
data class LocationDto(
    @SerialName("current_area") val currentArea: String? = null,
    @SerialName("current_area_name") val currentAreaName: String? = null,
    @SerialName("current_Region") val currentRegion: String? = null,
    @SerialName("current_Region_name") val currentRegionName: String? = null
)
