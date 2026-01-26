package com.example.delivaryUser.feature.outzonedelivery.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.bars.topbar.DelivaryUserTopBar
import com.example.delivaryUser.common.ui.components.buttons.DelivaryUserButtonPrimary
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.components.screen.DelivaryUserScreen
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.outzonedelivery.ui.viewmodel.OutZoneDeliveryContract
import com.example.delivaryUser.feature.outzonedelivery.ui.viewmodel.OutZoneDeliveryViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun OutSideZoneDeliveryScreen(viewModel: OutZoneDeliveryViewModel= koinViewModel()){
    val state by viewModel.state.collectAsStateWithLifecycle()

    OutSideZoneDeliveryScreenContent(state=state, action = viewModel::onActionTrigger)
}
@Composable
fun OutSideZoneDeliveryScreenContent(
    state: OutZoneDeliveryContract.State,
    action:(OutZoneDeliveryContract.Action) -> Unit
) {

    DelivaryUserScreen(
        header = {
            DelivaryUserTopBar(
                onStartIconClicked = { },
                content = { Text(stringResource(R.string.add_new_address)) },
            )
        },
        contentVerticalArrangement = Arrangement.Center,
        contentHorizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.outside_delivery_zone),
            style = DelivaryUserTheme.typography.headline.medium,
            color = DelivaryUserTheme.colors.secondary
        )
        Image(
            painter = painterResource(R.drawable.img_out_side_delivery_zone),
            contentDescription = "Image outside delivery zone",
            modifier = Modifier.padding(top = 30.dp)
        )
        Text(
            text = stringResource(R.string.dont_deliver_to_your_area_yet),
            style = DelivaryUserTheme.typography.label.large,
            color = DelivaryUserTheme.colors.secondary,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 34.dp, start = 46.dp, end = 46.dp)
        )

        DelivaryUserButtonPrimary(
            label = stringResource(R.string.vote_for_this_location),
            modifier = Modifier
                .padding(top = 34.dp, start = 17.dp, end = 17.dp)
                .fillMaxWidth(),
            onClick = { action(OutZoneDeliveryContract.Action.OnVoteClickAction)})

        Text(
            text = stringResource(R.string.change_location),
            style = DelivaryUserTheme.typography.headline.small,
            color = DelivaryUserTheme.colors.primary,
            modifier = Modifier
                .padding(top = 49.dp)
                .clickable { action(OutZoneDeliveryContract.Action.OnChangeLocation) })

    }
}

@Composable
@PreviewAllVariants
private fun OutSideZoneDeliveryScreenPreview() = DelivaryUserTheme {
    OutSideZoneDeliveryScreen()
}