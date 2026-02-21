package com.example.delivaryUser.feature.trackorder.ui.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.delivaryUser.common.ui.components.bars.topbar.DelivaryUserTopBar
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.components.screen.DelivaryUserScreen
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.orders.base.domain.models.domain.OrderStatus
import com.example.delivaryUser.feature.orders.base.ui.asString
import com.example.delivaryUser.feature.trackorder.ui.components.DeliverySteps
import com.example.delivaryUser.feature.trackorder.ui.components.MapSection
import com.example.delivaryUser.feature.trackorder.ui.components.OrderDetailsSheetContent
import com.example.delivaryUser.feature.trackorder.ui.components.TrackOrderDeliveryHeader
import com.example.delivaryUser.feature.trackorder.ui.viewmodel.TrackOrderContract
import com.example.delivaryUser.feature.trackorder.ui.viewmodel.TrackOrderViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TrackOrderScreen(viewModel: TrackOrderViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { number ->
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${number}"))
            context.startActivity(intent)
        }
    }

    TrackOrderContent(
        state = state, action = viewModel::onActionTrigger
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackOrderContent(
    state: TrackOrderContract.State, action: (TrackOrderContract.Action) -> Unit,
) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val sheetHeight = screenHeight * (296f / 733f)
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 150.dp,
        sheetContainerColor = DelivaryUserTheme.colors.background.surfaceHigh,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetContent = {
            OrderDetailsSheetContent(
                deliveryName = state.delivery.name,
                deliveryImg = state.delivery.image,
                deliveryFee = state.delivery.price,
                totalPrice = state.order.totalPrice,
                orderId = state.order.id,
                onCallClicked = { action(TrackOrderContract.Action.OnCallDriverClicked) },
                onChatClicked = { action(TrackOrderContract.Action.OnChatWithDriverClicked) },
                onCancelClick = {},
                priceOfUser = state.order.estimatedPrice,
                orderPrice = state.order.price,
                modifier = Modifier.height(sheetHeight)
            )
        }) {
        DelivaryUserScreen(
            header = {
                DelivaryUserTopBar(onStartIconClicked = { action(TrackOrderContract.Action.OnBackClicked) })
            }, contentScrollState = rememberScrollState()
        ) {
            TrackOrderDetails(
                client = state.client,
                deliveryTime = state.delivery.time,
                orderStatus = state.order.status
            )

            MapSection(
                delivery = state.delivery,
                orderType = state.order.type,
                client = state.client,
                isDataReady = state.isDataReady
            )

        }
    }
}

@Composable
private fun TrackOrderDetails(
    client: TrackOrderContract.Client, orderStatus: OrderStatus, deliveryTime: String
) {
    TrackOrderDeliveryHeader(client = client)
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 15.dp),
        color = DelivaryUserTheme.colors.background.disable.copy(alpha = 0.10f),
        thickness = 5.dp
    )
    DeliverySteps(
        currentStep = orderStatus, orderState = orderStatus.asString(), time = deliveryTime
    )
}

@Composable
@PreviewAllVariants
private fun TrackOrderContentPreview() = DelivaryUserTheme {
    TrackOrderContent(
        state = TrackOrderContract.State(), action = {})
}