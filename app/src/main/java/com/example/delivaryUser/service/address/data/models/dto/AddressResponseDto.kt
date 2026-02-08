package com.example.delivaryUser.service.address.data.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressResponseDto(
    @SerialName("message")
    val message: String? = null,
    @SerialName("address")
    val address: AddressDto? = null,
)
