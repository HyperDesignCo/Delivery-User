package com.example.delivaryUser.feature.trackorder.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.delivaryUser.common.ui.components.bars.topbar.DelivaryUserTopBar
import com.example.delivaryUser.common.ui.components.screen.DelivaryUserScreen
import com.example.delivaryUser.feature.trackorder.ui.components.DeliveryHeader
import com.example.delivaryUser.feature.trackorder.ui.components.DeliverySteps
import com.example.delivaryUser.feature.trackorder.ui.components.MapSection
import com.example.delivaryUser.feature.trackorder.ui.viewmodel.TrackOrderContract
import com.example.delivaryUser.feature.trackorder.ui.viewmodel.TrackOrderViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TrackOrderScreen(viewModel: TrackOrderViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    TrackOrderContent(
        state = state,
        action = viewModel::onActionTrigger
    )
}

@Composable
fun TrackOrderContent(
    state: TrackOrderContract.State,
    action: (TrackOrderContract.Action) -> Unit
) {
    DelivaryUserScreen(
        contentScrollState = rememberScrollState(),
        header = {
            DelivaryUserTopBar(
                onStartIconClicked = { action(TrackOrderContract.Action.BackClicked) }
            )
        },
        contentVerticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 0.dp),
    ) {
        DeliveryHeader(
            userName = state.userName,
            userAddress = state.userAddress,
            userImage = state.userImage
        )

        DeliverySteps(currentStep = state.currentStep, time = "7:15 PM")

        MapSection(
            onMapClicked = { action(TrackOrderContract.Action.MapClicked) }
        )
//
//        OrderDetailsCard(
//            state = state,
//            onCallClicked = { action(TrackOrderContract.Action.CallDriverClicked) },
//            onChatClicked = { action(TrackOrderContract.Action.ChatWithDriverClicked) }
//        )
    }
}