package com.example.delivaryUser.feature.trackorder.ui.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.delivaryUser.common.ui.components.bars.topbar.DelivaryUserTopBar
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.components.screen.DelivaryUserScreen
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.trackorder.ui.components.DeliveryHeader
import com.example.delivaryUser.feature.trackorder.ui.components.DeliverySteps
import com.example.delivaryUser.feature.trackorder.ui.components.MapSection
import com.example.delivaryUser.feature.trackorder.ui.components.OrderDetailsSheetContent
import com.example.delivaryUser.feature.trackorder.ui.viewmodel.TrackOrderContract
import com.example.delivaryUser.feature.trackorder.ui.viewmodel.TrackOrderViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TrackOrderScreen(viewModel: TrackOrderViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    TrackOrderContent(
        state = state, action = viewModel::onActionTrigger
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackOrderContent(
    state: TrackOrderContract.State, action: (TrackOrderContract.Action) -> Unit
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
                deliveryName = "Abdallah Alsayed",
                deliveryImg = "",
                deliveryFee = "233",
                totalPrice = "2332",
                orderStatus = "pending",
                providerImage = "",
                providerName = "Seoudi Supermarket",
                providerAddress = " El Sheikh Zayed - El Hay 9",
                orderId = "#123456789",
                onCallClicked = { action(TrackOrderContract.Action.CallDriverClicked) },
                onChatClicked = { action(TrackOrderContract.Action.ChatWithDriverClicked) },
                onCancelClick = {},
                modifier = Modifier.height(sheetHeight)
            )
        }) {
        DelivaryUserScreen(
            header = {
                DelivaryUserTopBar(
                    onStartIconClicked = { action(TrackOrderContract.Action.BackClicked) })
            }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                DeliveryHeader(
                    userName = "Abdallah Alsayed",
                    userAddress = "22 , Street 11, El Sheikh Zayed - El Hay 1",
                    userImage = state.userImage
                )

                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    color = DelivaryUserTheme.colors.background.disable,
                    thickness = 6.dp
                )

                DeliverySteps(
                    currentStep = state.currentStep, time = "7:15 PM"
                )

                MapSection(
                    onMapClicked = {
                        action(TrackOrderContract.Action.MapClicked)
                    },
                )
            }
        }
    }
}

@PreviewAllVariants
@Composable
private fun TrackOrderContentPreview() = DelivaryUserTheme {
    TrackOrderContent(
        state = TrackOrderContract.State(), action = {})
}