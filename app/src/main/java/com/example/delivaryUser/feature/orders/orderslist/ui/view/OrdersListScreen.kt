package com.example.delivaryUser.feature.orders.orderslist.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.bars.topbar.DelivaryUserTopBar
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.components.screen.DelivaryUserScreen
import com.example.delivaryUser.common.ui.components.screen.DeliveryUserEmptyScreen
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.orders.orderslist.ui.components.OrderCard
import com.example.delivaryUser.feature.orders.orderslist.ui.components.OrderRatingCard
import com.example.delivaryUser.feature.orders.orderslist.ui.viewmodel.OrdersContract
import com.example.delivaryUser.feature.orders.orderslist.ui.viewmodel.OrdersListViewModel
import org.koin.compose.koinInject

@Composable
fun OrdersScreen(viewModel: OrdersListViewModel = koinInject()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.onActionTrigger(OrdersContract.Action.Init)
    }
    OrdersContent(state, viewModel::onActionTrigger)
}

@Composable
private fun OrdersContent(state: OrdersContract.State, action: (OrdersContract.Action) -> Unit) {
    DelivaryUserScreen(
        header = {
            DelivaryUserTopBar(
                endIcon = R.drawable.ic_menu,
                onStartIconClicked = { action(OrdersContract.Action.OnBackClicked) },
                onEndIconClicked = {})
        },
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 48.dp)
    ) {
        if (state.orders.isEmpty()) {
            DeliveryUserEmptyScreen(
                icon = R.drawable.ic_empty_orders,
                text = stringResource(R.string.order_list_empty)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(
                    items = state.orders,
                    key = { _, order -> order.orderId }
                ) { index, order ->
                    val isLast = index == state.orders.lastIndex
                    val modifier = if (isLast) Modifier.padding(bottom = 32.dp) else Modifier
                    OrderCard(
                        modifier = modifier,
                        order = order,
                        onOrderClicked = { action(OrdersContract.Action.OrderClicked(order.orderId)) },
                        onRatingClicked = { action(OrdersContract.Action.OnOpenDialogClicked(order.orderId)) })
                }
            }
        }
        if (state.rate.isDialogVisible) OrderRatingCard(
            rate = state.rate,
            onCommentChanged = { action(OrdersContract.Action.OnCommentChanged(it)) },
            onSendClicked = { action(OrdersContract.Action.OnSendRateClicked) },
            onRatingClicked = { action(OrdersContract.Action.OnRatingClicked(it)) },
            onDismissClicked = { action(OrdersContract.Action.OnCloseDialogClicked) }
        )
    }
}

@Composable
@PreviewAllVariants
private fun OrdersScreenPreview() = DelivaryUserTheme {
    OrdersContent(
        state = OrdersContract.State(),
        action = {}
    )
}