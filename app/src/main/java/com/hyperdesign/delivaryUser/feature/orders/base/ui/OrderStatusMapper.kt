package com.hyperdesign.delivaryUser.feature.orders.base.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.hyperdesign.delivaryUser.feature.orders.base.domain.models.domain.OrderStatus

@Composable
fun OrderStatus.asString() = when (this) {
    OrderStatus.PENDING -> stringResource(R.string.order_is_pending)
    OrderStatus.ACCEPTED -> stringResource(R.string.order_is_accepted)
    OrderStatus.ON_WAY -> stringResource(R.string.order_is_on_the_way)
    OrderStatus.DELIVERY_ARRIVE -> stringResource(R.string.order_is_on_the_way)
    OrderStatus.DELIVERY_START -> stringResource(R.string.order_is_placed)
    OrderStatus.DELIVERED -> stringResource(R.string.order_is_delivered)
    OrderStatus.CANCELED -> stringResource(R.string.order_is_cancelled)
    OrderStatus.UNKNOWN -> ""
}
@Composable
fun OrderStatus.toColor() = when (this) {
    OrderStatus.PENDING -> DelivaryUserTheme.colors.status.yellowAccent
    OrderStatus.ACCEPTED -> DelivaryUserTheme.colors.status.greenAccent
    OrderStatus.ON_WAY -> DelivaryUserTheme.colors.status.orangeAccent
    OrderStatus.DELIVERY_ARRIVE -> DelivaryUserTheme.colors.status.orangeAccent
    OrderStatus.DELIVERY_START -> DelivaryUserTheme.colors.status.grayAccent
    OrderStatus.DELIVERED -> DelivaryUserTheme.colors.status.blueAccent
    OrderStatus.CANCELED -> DelivaryUserTheme.colors.status.redAccent
    OrderStatus.UNKNOWN -> Color.Unspecified
}