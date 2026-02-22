package com.hyperdesign.delivaryUser.feature.authentication.forgetpassword.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForgetPasswordDto(
    @SerialName("message")
    val message: String? = null,
    @SerialName("code")
    val code: String? = null
)