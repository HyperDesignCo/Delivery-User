package com.example.delivaryUser.feature.deliveryoutzone.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.bars.topbar.DelivaryUserTopBar
import com.example.delivaryUser.common.ui.components.buttons.DelivaryUserButtonPrimary
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.components.screen.DelivaryUserScreen
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.deliveryoutzone.ui.viewmodel.DeliveryOutZoneContract
import com.example.delivaryUser.feature.deliveryoutzone.ui.viewmodel.DeliveryOutZoneViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun DeliveryOutZoneScreen(viewModel: DeliveryOutZoneViewModel = koinViewModel()) {
    DeliveryOutZoneContent(action = viewModel::onActionTrigger)
}

@Composable
private fun DeliveryOutZoneContent(action: (DeliveryOutZoneContract.Action) -> Unit, ) {
    DelivaryUserScreen(
        header = { DelivaryUserTopBar(startIcon = null) },
        contentPadding = PaddingValues(horizontal = 16.dp),
        contentHorizontalAlignment = Alignment.CenterHorizontally,
        contentVerticalArrangement = Arrangement.spacedBy(34.dp, alignment = Alignment.CenterVertically)
    ) {
        Text(
            text = stringResource(R.string.outside_delivery_zone),
            style = DelivaryUserTheme.typography.headline.medium,
            color = DelivaryUserTheme.colors.secondary
        )
        Image(
            painter = painterResource(R.drawable.img_out_side_delivery_zone),
            contentDescription = null,
        )
        Text(
            text = stringResource(R.string.do_not_deliver_to_your_area_yet),
            style = DelivaryUserTheme.typography.label.large,
            color = DelivaryUserTheme.colors.secondary,
            minLines = 3,
            textAlign = TextAlign.Center,
        )

        DelivaryUserButtonPrimary(
            modifier = Modifier
                .fillMaxWidth()
                .dropShadow(
                    shape = CircleShape,
                    shadow = Shadow(
                        radius = 10.dp,
                        spread = 6.dp,
                        color = DelivaryUserTheme.colors.shadow
                    )
                ),
            label = stringResource(R.string.vote_for_this_location),
            onClick = { action(DeliveryOutZoneContract.Action.OnVoteClicked) })

        Text(
            modifier = Modifier.clickable { action(DeliveryOutZoneContract.Action.OnChangeLocationClicked) },
            text = stringResource(R.string.change_location),
            style = DelivaryUserTheme.typography.headline.small,
            color = DelivaryUserTheme.colors.primary
        )
    }
}

@Composable
@PreviewAllVariants
private fun DeliveryOutZoneScreenPreview() = DelivaryUserTheme {
    DeliveryOutZoneContent(action = {})
}