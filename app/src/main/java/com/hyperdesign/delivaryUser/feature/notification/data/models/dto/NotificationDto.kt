package com.hyperdesign.delivaryUser.feature.notification.data.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationDto(
    @SerialName("id")
    val id: String?=null,
    @SerialName("user_id")
    val userId: String?=null,
    @SerialName("title")
    val title: String?=null,
    @SerialName("text")
    val text: String?=null
)