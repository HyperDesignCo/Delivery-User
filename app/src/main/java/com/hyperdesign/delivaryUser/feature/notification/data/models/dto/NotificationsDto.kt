package com.hyperdesign.delivaryUser.feature.notification.data.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationsDto(
    @SerialName("message")
    val message: String? = null,
    @SerialName("Notifications")
    val notifications: List<NotificationDto>,
)