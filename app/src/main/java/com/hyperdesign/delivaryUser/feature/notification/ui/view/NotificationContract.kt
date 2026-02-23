package com.hyperdesign.delivaryUser.feature.notification.ui.view

import com.hyperdesign.delivaryUser.feature.notification.domain.models.Notification

sealed interface NotificationContract {
    sealed interface Action : NotificationContract {
        object GetNotifications : Action
    }

    data class State(
        val notifications: List<Notification> = emptyList(),
    ): NotificationContract
}