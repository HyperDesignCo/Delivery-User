package com.hyperdesign.delivaryUser.feature.notification.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.hyperdesign.delivaryUser.common.ui.loading.ILoadingEvent
import com.hyperdesign.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.hyperdesign.delivaryUser.feature.notification.domain.interactors.GetNotifications
import com.hyperdesign.delivaryUser.feature.notification.domain.models.Notification
import com.hyperdesign.delivaryUser.feature.notification.ui.view.NotificationContract
import kotlinx.coroutines.launch

class NotificationViewModel(private val notifications: GetNotifications) :
    BaseViewModel<NotificationContract.State, NotificationContract.Action>(NotificationContract.State()) {
    override fun onActionTrigger(action: NotificationContract.Action) {
        when (action) {
            NotificationContract.Action.GetNotifications -> getNotifications()
        }
    }

    private fun getNotifications() {
        viewModelScope.launch {
            notifications.invoke(Unit).collectResource(
                onSuccess = ::onGetNotificationsSuccess,
                onLoading = {
                    fireLoading(ILoadingEvent.CircularProgressIndicator(it))
                }
            )
        }
    }

    private fun onGetNotificationsSuccess(notifications: List<Notification>) {
        updateState {
            copy(notifications = notifications)
        }
    }
}