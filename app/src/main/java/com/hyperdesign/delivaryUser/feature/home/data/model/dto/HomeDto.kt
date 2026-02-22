package com.hyperdesign.delivaryUser.feature.home.data.model.dto

import com.hyperdesign.delivaryUser.feature.orders.base.data.models.dto.OrderDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeDto(
    @SerialName("message")
    val message: String? = null,
    @SerialName("ads")
    val ads: List<AdDto>? = null,
    @SerialName("orders")
    val orders: List<OrderDto>? = null,
)