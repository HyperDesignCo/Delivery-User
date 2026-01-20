package com.example.delivaryUser.feature.home.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdsDto(
    @SerialName("message")
    val message: String? = null,
    @SerialName("ads")
    val ads: List<AdDto>? = null,
)