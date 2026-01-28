package com.example.delivaryUser.feature.pointtopoint.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.bars.topbar.DelivaryUserTopBar
import com.example.delivaryUser.common.ui.components.buttons.DelivaryUserButtonPrimary
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.components.screen.DelivaryUserScreen
import com.example.delivaryUser.common.ui.extension.clickableWithNoRipple
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.pointtopoint.ui.components.PointToPointForm
import com.example.delivaryUser.feature.pointtopoint.ui.viewmodel.PointToPointContract
import com.example.delivaryUser.feature.pointtopoint.ui.viewmodel.PointToPointViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PointToPointScreen(viewModel: PointToPointViewModel = koinViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.refresh()
    }
    val state by viewModel.state.collectAsStateWithLifecycle()
    PointToPointContent(state = state, action = viewModel::onActionTrigger)
}

@Composable
private fun PointToPointContent(state: PointToPointContract.State, action: (PointToPointContract.Action) -> Unit) {
    DelivaryUserScreen(header = {
        DelivaryUserTopBar(onStartIconClicked = { action(PointToPointContract.Action.OnBackClicked) }, content = {
            Text(
                text = stringResource(id = R.string.where_is_it_going),
                style = DelivaryUserTheme.typography.headline.large,
                color = DelivaryUserTheme.colors.background.surfaceHigh
            )
        })
    }, contentScrollState = rememberScrollState()) {
        AddressesSection(state = state, action = action)
        PointToPointForm(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 32.dp),
            estimatedPrice = state.estimatedPrice,
            comment = state.comment,
            deliveryFees = state.deliveryFees,
            orderTypes = state.orderTypes,
            selectedOrderType = state.selectedOrderType,
            onOrderTypeChange = { action(PointToPointContract.Action.ChooseOrderType(it)) },
            onEstimatedPriceChange = { action(PointToPointContract.Action.OnEstimatedPriceChange(it)) },
            onCommentChange = { action(PointToPointContract.Action.OnCommentChange(it)) },
            expanded = state.isExpanded,
            onDismissMenuClicked = { action(PointToPointContract.Action.DismissDropDownMenu) },
            onExpandClicked = { action(PointToPointContract.Action.ExpandDropDownMenu) }
        )
        Spacer(modifier = Modifier.weight(1f))
        DelivaryUserButtonPrimary(
            modifier = Modifier.padding(horizontal = 16.dp),
            label = stringResource(R.string.checkout),
            onClick = { action(PointToPointContract.Action.OnCheckOutClicked) }
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun AddressesSection(
    state: PointToPointContract.State,
    action: (PointToPointContract.Action) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .dropShadow(
                shape = RectangleShape,
                shadow = Shadow(
                    radius = 6.dp,
                    spread = 1.dp,
                    color = DelivaryUserTheme.colors.shadow
                )
            )
            .background(color = DelivaryUserTheme.colors.secondary.copy(alpha = 0.01f))
            .padding(start = 16.dp, end = 22.dp, top = 22.dp, bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(9.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_point_to_point_address),
            tint = DelivaryUserTheme.colors.secondary,
            contentDescription = null
        )
        Column(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.weight(1f)) {
            AddressItem(
                label = state.senderAddress.addressName.ifBlank { stringResource(R.string.choose_sender_address) },
                onClick = { action(PointToPointContract.Action.ChooseSenderAddress) })
            AddressItem(
                label = state.receiverAddress.addressName.ifBlank { stringResource(R.string.choose_recipient_Address) },
                onClick = { action(PointToPointContract.Action.ChooseReceiverAddress) })
        }
        Column(verticalArrangement = Arrangement.spacedBy(9.dp)) {
            AddAddress({ action(PointToPointContract.Action.AddSenderAddress) })
            AddAddress({ action(PointToPointContract.Action.AddReceiverAddress) })
        }
    }
}

@Composable
private fun AddressItem(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier
            .dropShadow(
                shape = CircleShape,
                shadow = Shadow(
                    radius = 8.dp,
                    spread = 1.dp,
                    color = DelivaryUserTheme.colors.shadow
                )
            )
            .background(
                color = DelivaryUserTheme.colors.background.surfaceHigh,
                shape = RoundedCornerShape(20.dp)
            )
            .fillMaxWidth()
            .padding(vertical = 11.dp, horizontal = 16.dp)
            .clickableWithNoRipple {
                onClick()
            },
        color = DelivaryUserTheme.colors.secondary,
        text = label,
        style = DelivaryUserTheme.typography.body.small,
    )
}

@Composable
private fun AddAddress(onAddLocationClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .dropShadow(
                shape = CircleShape,
                shadow = Shadow(
                    radius = 6.dp,
                    spread = 1.dp,
                    color = DelivaryUserTheme.colors.shadow
                )
            )
            .background(
                color = DelivaryUserTheme.colors.background.surfaceHigh,
                shape = CircleShape
            )
            .clickableWithNoRipple {
                onAddLocationClicked()
            },
        contentAlignment = Alignment.Center

    ) {
        Icon(
            modifier = Modifier
                .padding(13.dp)
                .size(16.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_rounded_add), contentDescription = null
        )
    }
}

@Composable
@PreviewAllVariants
fun PointToPointScreenPreview() = DelivaryUserTheme {
    PointToPointContent(state = PointToPointContract.State(), action = {})
}

