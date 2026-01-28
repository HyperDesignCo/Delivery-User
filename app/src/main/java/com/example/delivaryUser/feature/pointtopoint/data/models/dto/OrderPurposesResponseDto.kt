package com.example.delivaryUser.feature.pointtopoint.data.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderPurposesResponseDto(
    @SerialName("message")
    val message: String? = null,
    @SerialName("OrderPurposes")
    val orderPurposes: List<OrderPurposeDto>? = null,
)