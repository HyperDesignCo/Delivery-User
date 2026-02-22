package com.hyperdesign.delivaryUser.feature.orders.base.data.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrdersDto(
    @SerialName("message")
    val message: String? = null,
    @SerialName("orders")
    val orders: List<OrderDto>,
)