package com.example.delivaryUser.feature.authentication.verifyOtp.data.models.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResendOtpRequest(
    @SerialName("phone")
    val phone: String = "",
)
