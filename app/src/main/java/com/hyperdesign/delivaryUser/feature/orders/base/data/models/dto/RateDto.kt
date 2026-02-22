package com.hyperdesign.delivaryUser.feature.orders.base.data.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RateDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("rate")
    val rate: Int? = null,
    @SerialName("comment")
    val comment: String? = null,
    @SerialName("created_at")
    val createdAt: String? = null,
    @SerialName("updated_at")
    val updatedAt: String? = null,
    @SerialName("order_id")
    val orderId: Int? = null,
    @SerialName("user_id")
    val userId: Int? = null
)