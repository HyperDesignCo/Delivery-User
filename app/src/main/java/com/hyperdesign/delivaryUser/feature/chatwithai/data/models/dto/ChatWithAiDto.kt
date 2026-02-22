package com.hyperdesign.delivaryUser.feature.chatwithai.data.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatWithAiDto(
    @SerialName("html")
    val html: String? = null,
)