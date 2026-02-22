package com.example.delivaryUser.service.location.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class LocationEntity(
    val currentArea: String = "",
    val currentAreaName: String = "",
    val currentRegion: String = "",
    val currentAreaDeliveryCost: String = "",
    val currentRegionName: String = "",
)
