package com.example.delivaryUser.feature.orders.base.data.models.dto

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
    val createdAt: String? = null
)