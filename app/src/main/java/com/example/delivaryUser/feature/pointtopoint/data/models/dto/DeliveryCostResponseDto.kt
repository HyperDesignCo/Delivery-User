package com.example.delivaryUser.feature.pointtopoint.data.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeliveryCostResponseDto(
    @SerialName("deliveryCost")
    val deliveryCost: Double? = null,
    @SerialName("distance")
    val distance: Double? = null,
    @SerialName("message")
    val message: String? = null,
)