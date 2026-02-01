package com.example.delivaryUser.service.location.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CheckLocation(
    val message: String,
    val data: Location? = null,
)

@Serializable
data class Location(
    val currentArea: String? = null,
    val currentAreaName: String? = null,
    val currentRegion: String? = null,
    val currentRegionName: String? = null
)