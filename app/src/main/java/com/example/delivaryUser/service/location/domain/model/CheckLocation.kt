package com.example.delivaryUser.service.location.domain.model

import kotlinx.serialization.Serializable

data class CheckLocation(
    val message: String,
    val data: Address? = null,
)

@Serializable
data class Address(
    val currentArea: String? = null,
    val currentAreaName: String? = null,
    val currentRegion: String? = null,
    val currentRegionName: String? = null
)
