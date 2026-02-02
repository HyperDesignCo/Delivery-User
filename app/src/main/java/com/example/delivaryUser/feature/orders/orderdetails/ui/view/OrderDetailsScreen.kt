package com.example.delivaryUser.feature.orders.orderdetails.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.bars.topbar.DelivaryUserTopBar
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.components.screen.DelivaryUserScreen
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.orders.base.domain.models.domain.OrderStatus
import com.example.delivaryUser.feature.orders.orderdetails.ui.viewmodel.OrderDetailsContract
import com.example.delivaryUser.feature.orders.orderdetails.ui.viewmodel.OrderDetailsViewModel
import org.koin.androidx.compose.koinViewModel

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
fun OrderDetailsScreen(viewModel: OrderDetailsViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    OrderDetailsContent(state = state, action = viewModel::onActionTrigger)
}

@Composable
fun OrderDetailsContent(state: OrderDetailsContract.State, action: (OrderDetailsContract.Action) -> Unit) {
    DelivaryUserScreen(
        header = {
            DelivaryUserTopBar({ action(OrderDetailsContract.Action.OnBackClicked) })
        },
        contentHorizontalAlignment = Alignment.Start,
        contentPadding = PaddingValues(16.dp),
        contentVerticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = state.orderState.asString(),
            style = DelivaryUserTheme.typography.headline.medium,
            color = DelivaryUserTheme.colors.secondary
        )
        OrderDetailsCard(order = state)
    }
}

@Composable
private fun OrderDetailsCard(order: OrderDetailsContract.State) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = DelivaryUserTheme.colors.background.surfaceHigh, shape = RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = DelivaryUserTheme.colors.background.hint.copy(0.1f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 12.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        OrderDetails()
        OrderDetailsItem(
            label = stringResource(R.string.order_number),
            value = order.orderNumber,
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = DelivaryUserTheme.colors.background.hint.copy(0.1f)
        )
        OrderMainData(
            firstLabel = stringResource(R.string.provider_name),
            firstValue = order.providerName,
            secondLabel = stringResource(R.string.client_name),
            secondValue = order.clientName
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = DelivaryUserTheme.colors.background.hint.copy(0.1f)
        )
        OrderMainData(
            firstLabel = stringResource(R.string.delivery_name),
            firstValue = order.deliveryName,
            secondLabel = stringResource(R.string.delivery_number),
            secondValue = order.deliveryNumber
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = DelivaryUserTheme.colors.background.hint.copy(0.1f)
        )
        if(order.estimatedPrice.isNotBlank())
        OrderDetailsItem(
            label = stringResource(R.string.user_estimated_price),
            value = order.estimatedPrice.plus(" ").plus(stringResource(R.string.egp)),
        )
        OrderMainData(
            firstLabel = stringResource(R.string.order_price),
            firstValue = order.orderPrice.plus(" ").plus(stringResource(R.string.egp)),
            secondLabel = stringResource(R.string.delivery),
            secondValue = order.deliveryPrice.plus(" ").plus(stringResource(R.string.egp))
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = DelivaryUserTheme.colors.background.hint.copy(0.1f)
        )
        OrderTotalPrice(totalPrice = order.totalPrice)
    }
}

@Composable
private fun OrderDetails(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_order_details),
            tint = DelivaryUserTheme.colors.secondary,
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.order_details),
            style = DelivaryUserTheme.typography.body.small,
            color = DelivaryUserTheme.colors.secondary
        )
    }
}

@Composable
private fun OrderMainData(
    firstLabel: String,
    firstValue: String,
    secondLabel: String,
    secondValue: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OrderDetailsItem(
            label = firstLabel,
            value = firstValue,
        )
        OrderDetailsItem(
            label = secondLabel,
            value = secondValue,
        )
    }
}

@Composable
private fun OrderDetailsItem(label: String, value: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = DelivaryUserTheme.typography.label.medium,
            color = DelivaryUserTheme.colors.secondary
        )
        Text(
            text = value,
            style = DelivaryUserTheme.typography.label.medium,
            color = DelivaryUserTheme.colors.secondary
        )
    }
}

@Composable
private fun OrderTotalPrice(
    totalPrice: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.total),
            style = DelivaryUserTheme.typography.title.medium,
            color = DelivaryUserTheme.colors.secondary
        )
        Text(
            text = stringResource(R.string.egp).plus(" ").plus(totalPrice),
            style = DelivaryUserTheme.typography.title.medium,
            color = DelivaryUserTheme.colors.secondary
        )
    }
}

@Composable
@PreviewAllVariants
private fun OrderDetailsPreview() = DelivaryUserTheme {
    OrderDetailsContent(
        state = OrderDetailsContract.State(
            orderNumber = "15253364",
            providerName = "seoudi supermarket ",
            clientName = "Rodina Mobark",
            deliveryName = "Rodina Mobark",
            deliveryNumber = "011111111111",
            orderPrice = "400.0",
            deliveryPrice = "25.00",
            totalPrice = "25.00"
        ), action = {})
}