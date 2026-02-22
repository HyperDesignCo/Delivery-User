package com.hyperdesign.delivaryUser.service.address.data.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressesResponseDto(
    @SerialName("message")
    val message: String? = null,
    @SerialName("address")
    val address: List<AddressDto>? = null,
)