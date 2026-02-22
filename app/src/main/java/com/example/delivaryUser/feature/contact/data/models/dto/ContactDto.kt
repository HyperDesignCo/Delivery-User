package com.example.delivaryUser.feature.contact.data.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContactDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("support_phone")
    val supportPhone: String? = null,
    @SerialName("support_email")
    val supportEmail: String? = null,
    @SerialName("support_whatsapp")
    val supportWhatsapp: String? = null,
    @SerialName("facebook")
    val facebook: String? = null,
    @SerialName("x")
    val x: String? = null,
    @SerialName("instgram")
    val instagram: String? = null,
    @SerialName("tiktok")
    val tiktok: String? = null,
)