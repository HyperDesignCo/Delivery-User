package com.hyperdesign.delivaryUser.feature.notification.data.repository

import com.hyperdesign.delivaryUser.feature.notification.data.mapper.NotificationMapper
import com.hyperdesign.delivaryUser.feature.notification.domain.models.Notification
import com.hyperdesign.delivaryUser.feature.notification.domain.repository.INotificationRepository
import com.hyperdesign.delivaryUser.feature.notification.domain.repository.remote.INotificationRemoteDataSource

class NotificationRepository(private val remote: INotificationRemoteDataSource) : INotificationRepository {
    override suspend fun getNotifications(): List<Notification> =
        NotificationMapper.dtoToDomain(list = remote.getNotifications().notifications)
}