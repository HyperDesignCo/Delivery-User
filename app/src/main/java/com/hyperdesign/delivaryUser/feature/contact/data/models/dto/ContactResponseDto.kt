package com.hyperdesign.delivaryUser.feature.contact.data.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContactResponseDto(
    @SerialName("Contactus")
    val contacts: ContactDto?=null,
    @SerialName("message")
    val message: String?=null,
)