package com.hyperdesign.delivaryUser.feature.notification.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.ui.components.bars.topbar.DelivaryUserTopBar
import com.hyperdesign.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.hyperdesign.delivaryUser.common.ui.components.screen.DelivaryUserScreen
import com.hyperdesign.delivaryUser.common.ui.components.screen.DeliveryUserEmptyScreen
import com.hyperdesign.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.hyperdesign.delivaryUser.feature.notification.domain.models.Notification
import com.hyperdesign.delivaryUser.feature.notification.ui.viewmodel.NotificationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NotificationScreen(viewModel: NotificationViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.onActionTrigger(action = NotificationContract.Action.GetNotifications)
    }
    NotificationContent(state = state, action = viewModel::onActionTrigger)
}

@Composable
private fun NotificationContent(state: NotificationContract.State, action: (NotificationContract.Action) -> Unit) {
    DelivaryUserScreen(
        header = {
            DelivaryUserTopBar(
                startIcon = null,
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text(
                        text = stringResource(id = R.string.notifications),
                        style = DelivaryUserTheme.typography.headline.large,
                        color = DelivaryUserTheme.colors.background.surfaceHigh
                    )
                }
            }
        },
        contentHorizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(16.dp)
    ) {
        if (state.notifications.isEmpty()) {
            DeliveryUserEmptyScreen(
                icon = R.drawable.ic_empty_orders,
                text = stringResource(R.string.empty_notification)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = state.notifications,
                    key = { notification -> notification.id }
                ) { notification ->
                    val isLast = notification == state.notifications.last()
                    val modifier = if (isLast) Modifier.padding(bottom = 32.dp) else Modifier
                    NotificationItem(notification = notification, modifier = modifier)
                }
            }
        }
    }

}

@Composable
private fun NotificationItem(modifier: Modifier = Modifier, notification: Notification) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = DelivaryUserTheme.colors.background.surfaceHigh, shape = RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = DelivaryUserTheme.colors.background.hint.copy(0.1f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = notification.title,
            style = DelivaryUserTheme.typography.body.small,
            color = DelivaryUserTheme.colors.secondary
        )
        Text(
            text = notification.text,
            style = DelivaryUserTheme.typography.body.small,
            color = DelivaryUserTheme.colors.secondary
        )
    }
}

@Composable
@PreviewAllVariants
private fun NotificationScreenPreview() = DelivaryUserTheme {
    val notifications = List(10) { id ->
        Notification(
            id = id.toString(),
            userId = "1",
            title = "New Order",
            text = "You have a new order",
        )
    }

    NotificationContent(
        state = NotificationContract.State(
            notifications
        ),
        action = {}
    )
}