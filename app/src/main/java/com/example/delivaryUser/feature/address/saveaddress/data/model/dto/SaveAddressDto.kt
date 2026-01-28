package com.example.delivaryUser.feature.address.saveaddress.data.model.dto

import com.example.delivaryUser.service.address.data.models.dto.AddressDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SaveAddressDto(
    @SerialName("message") val message: String,
    @SerialName("address") val address: AddressDto

)
