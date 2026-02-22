package com.hyperdesign.delivaryUser.service.location.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CheckLocation(
    val message: String,
    val data: Location,
)

@Serializable
data class Location(
    val currentArea: String,
    val currentAreaName: String,
    val currentAreaDeliveryCost: String,
    val currentRegion: String,
    val currentRegionName: String,
)