package com.hyperdesign.delivaryUser.feature.deliveryoutzone.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddAreaDto(
    @SerialName("lat") val latitude: String?=null,
    @SerialName("lng") val longitude: String?=null,
    @SerialName("updated_at") val updatedAt: String?=null,
    @SerialName("created_at") val createdAt: String?=null,
    @SerialName("id") val id: Int?=null
)
