package com.hyperdesign.delivaryUser.feature.notification.domain.repository.remote

import com.hyperdesign.delivaryUser.feature.notification.data.models.dto.NotificationsDto

interface INotificationRemoteDataSource {
    suspend fun getNotifications() : NotificationsDto
}