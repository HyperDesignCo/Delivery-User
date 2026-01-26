package com.example.delivaryUser.feature.outzonedelivery.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddAreaRequest(
    @SerialName("lat") val latitude: String,
    @SerialName("lng") val longitude: String,
)
