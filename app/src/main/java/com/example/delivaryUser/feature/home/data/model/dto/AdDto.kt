package com.example.delivaryUser.feature.home.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("order_by")
    val orderBy: String? = null,
    @SerialName("lang")
    val lang: String? = null,
    @SerialName("image")
    val image: String? = null,
)