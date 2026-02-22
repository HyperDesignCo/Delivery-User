package com.example.delivaryUser.feature.orders.base.data.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewOrderResponseDto(
    @SerialName("message")
    val message: String,
    @SerialName("order")
    val order: OrderDto,
)