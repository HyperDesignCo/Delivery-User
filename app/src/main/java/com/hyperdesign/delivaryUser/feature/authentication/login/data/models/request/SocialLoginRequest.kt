package com.hyperdesign.delivaryUser.feature.authentication.login.data.models.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SocialLoginRequest(
    @SerialName("email")
    val email: String,
    @SerialName("name")
    val name: String,
    @SerialName("device_token")
    val deviceToken: String,
    @SerialName("social")
    val social: String,
    @SerialName("social_id")
    val socialId: String,
    @SerialName("device_type")
    val deviceType: String = "android"
)