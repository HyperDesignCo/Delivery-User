package com.example.delivaryUser.feature.userdata.editaccount.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EditAccountDto(
    @SerialName("message")
    val message: String,
)