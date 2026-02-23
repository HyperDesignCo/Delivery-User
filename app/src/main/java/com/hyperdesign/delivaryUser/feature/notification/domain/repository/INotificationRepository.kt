package com.hyperdesign.delivaryUser.feature.notification.domain.repository

import com.hyperdesign.delivaryUser.feature.notification.domain.models.Notification

interface INotificationRepository {
    suspend fun getNotifications(): List<Notification>
}