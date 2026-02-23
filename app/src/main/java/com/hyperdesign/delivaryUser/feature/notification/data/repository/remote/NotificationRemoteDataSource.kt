package com.hyperdesign.delivaryUser.feature.notification.data.repository.remote

import com.hyperdesign.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import com.hyperdesign.delivaryUser.feature.notification.data.models.dto.NotificationsDto
import com.hyperdesign.delivaryUser.feature.notification.domain.repository.remote.INotificationRemoteDataSource

class NotificationRemoteDataSource(private val provider: IRemoteDataSourceProvider) : INotificationRemoteDataSource {
    override suspend fun getNotifications(): NotificationsDto = provider.get(
        endpoint = NOTIFICATIONS_END_POINT,
        serializer = NotificationsDto.serializer()
    )

    companion object {
        const val NOTIFICATIONS_END_POINT = "Usernotifications"
    }
}