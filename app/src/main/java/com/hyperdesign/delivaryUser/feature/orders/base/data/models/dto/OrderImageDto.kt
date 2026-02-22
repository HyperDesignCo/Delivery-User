package com.hyperdesign.delivaryUser.feature.orders.base.data.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderImageDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("order_id")
    val orderId: String? = null,
    @SerialName("image")
    val image: String? = null,
)