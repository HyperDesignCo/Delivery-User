package com.hyperdesign.delivaryUser.feature.authentication.verifyphone.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VerifyPhoneDto(
    @SerialName("message")
    val message: String? = null,
    @SerialName("code")
    val code: String? = null
)
