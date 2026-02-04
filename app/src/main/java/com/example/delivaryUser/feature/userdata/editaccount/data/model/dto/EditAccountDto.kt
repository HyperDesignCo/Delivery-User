package com.example.delivaryUser.feature.userdata.editaccount.data.model.dto

import com.example.delivaryUser.service.user.data.dto.UserDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EditAccountDto(
    @SerialName("message")
    val message: String? = null,
    @SerialName("user")
    val user: UserDto? = null,
)