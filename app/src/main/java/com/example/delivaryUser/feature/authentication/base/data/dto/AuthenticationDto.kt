package com.example.delivaryUser.feature.authentication.base.data.dto

import com.example.delivaryUser.service.user.data.dto.UserDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationDto(
    @SerialName("message")
    val message: String? = null,
    @SerialName("user")
    val user: UserDto? = null,
    @SerialName("code")
    val code: String? = null,
    @SerialName("access_token")
    val accessToken: String? = null,
)