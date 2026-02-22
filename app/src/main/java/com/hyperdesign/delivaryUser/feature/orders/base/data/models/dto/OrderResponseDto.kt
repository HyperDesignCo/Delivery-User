package com.hyperdesign.delivaryUser.feature.orders.base.data.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderResponseDto(
    @SerialName("message")
    val message: String,
    @SerialName("orders")
    val order: OrderDto,
)