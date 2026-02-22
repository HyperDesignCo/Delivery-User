package com.example.delivaryUser.feature.authentication.verifyOtp.data.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OtpDto(
    @SerialName("message")
    val message: String? = null,
)