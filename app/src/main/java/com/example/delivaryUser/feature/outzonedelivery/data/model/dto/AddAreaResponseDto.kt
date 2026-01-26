package com.example.delivaryUser.feature.outzonedelivery.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddAreaResponseDto(
    @SerialName("message") val message: String,
    @SerialName("AreaRequest") val areaRequest: AreaRequestDto? = null
)

@Serializable
data class AreaRequestDto(
    @SerialName("lat") val latitude: String,
    @SerialName("lng") val longitude: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("id") val id: Int

)
