package com.example.delivaryUser.feature.orders.orderslist.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.extension.clickableWithNoRipple
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.orders.base.domain.models.domain.OrderStatus
import com.example.delivaryUser.feature.orders.orderslist.ui.viewmodel.OrdersContract


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
fun OrderCard(
    order: OrdersContract.OrderUiState,
    onOrderClicked: (Int) -> Unit,
    onRatingClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = order.orderState.toColor(), shape = RoundedCornerShape(8.dp)
                )
                .clickableWithNoRipple {
                    onOrderClicked(order.orderId)
                }
                .padding(16.dp)
                .padding(bottom = 40.dp),
        ) {
            OrderState(orderState = order.orderState.asString(), orderDate = order.orderDate)
            Text(
                text = stringResource(R.string.from).plus(" ").plus(order.provider),
                style = DelivaryUserTheme.typography.body.small,
                color = DelivaryUserTheme.colors.secondary
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape),
                    model = order.delivary.deliveryImage,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                OrderMainDate(
                    deliveryName = order.delivary.deliveryName,
                    stars = order.stars,
                    orderId = order.orderId,
                    orderPrice = order.orderPrice
                )
            }
        }
        OrderRating(
            modifier = Modifier.align(Alignment.BottomCenter),
            onRatingClicked = { onRatingClicked() })
    }
}

@Composable
private fun OrderState(orderState: String, orderDate: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = orderState,
            style = DelivaryUserTheme.typography.headline.medium,
            color = DelivaryUserTheme.colors.secondary
        )
        Text(
            text = orderDate,
            style = DelivaryUserTheme.typography.label.medium,
            color = DelivaryUserTheme.colors.secondary
        )
    }
}

@Composable
private fun OrderMainDate(
    deliveryName: String,
    stars: String,
    orderId: Int,
    orderPrice: String,
) {
    Column(modifier = Modifier.padding(start = 7.dp)) {
        Text(
            text = deliveryName,
            style = DelivaryUserTheme.typography.title.large,
            color = DelivaryUserTheme.colors.secondary
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            if (stars.isNotBlank())
                for (i in 0..4) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_star),
                        contentDescription = null,
                        tint = if (stars.toInt() > i) DelivaryUserTheme.colors.status.accentColor else DelivaryUserTheme.colors.background.hint.copy(
                            alpha = 0.2f
                        )
                    )
                }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.order).plus(" ").plus("#").plus(orderId),
                style = DelivaryUserTheme.typography.body.small,
                color = DelivaryUserTheme.colors.secondary
            )
            Text(
                text = stringResource(R.string.egp).plus(" ").plus(orderPrice),
                style = DelivaryUserTheme.typography.title.large,
                color = DelivaryUserTheme.colors.primary
            )
        }
    }
}

@Composable
@Preview
private fun OrderCardPreview() = DelivaryUserTheme {
    OrderCard(
        order = OrdersContract.OrderUiState(
            orderState = OrderStatus.PENDING,
            orderDate = "Oct 13 . 11:56pm", orderId = 15253364,
            orderPrice = "199.39",
            provider = "seoudi supermarket ",
            delivary = OrdersContract.DeliveryUiState(
                deliveryName = "Ahmed sayed ",
                deliveryImage = ""
            ),
            stars = "4",

            ),
        onOrderClicked = {},
        onRatingClicked = {}
    )
}