package com.hyperdesign.delivaryUser.feature.deliveryoutzone.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddAreaResponseDto(
    @SerialName("message") val message: String?=null,
    @SerialName("AreaRequest") val areaRequest: AddAreaDto? = null
)