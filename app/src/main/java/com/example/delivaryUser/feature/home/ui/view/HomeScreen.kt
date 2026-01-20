package com.example.delivaryUser.feature.home.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.bars.topbar.DelivaryUserTopBar
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.components.screen.DelivaryUserScreen
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.home.ui.components.HomeAdsSlider
import com.example.delivaryUser.feature.home.ui.components.HomeCard
import com.example.delivaryUser.feature.home.ui.components.HomeLocation
import com.example.delivaryUser.feature.home.ui.viewmodel.HomeContract
import com.example.delivaryUser.feature.home.ui.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeContent(state = state, action = viewModel::onActionTrigger)
}

@Composable
fun HomeContent(state: HomeContract.State, action: (HomeContract.Action) -> Unit) {
    DelivaryUserScreen(
        header = {
            DelivaryUserTopBar({})
        },
        contentVerticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        HomeLocation(
            location = state.location,
            onLocationClicked = { action(HomeContract.Action.OnLocationClicked(state.location)) },
            onAddLocationClicked = { action(HomeContract.Action.OnAddLocationClicked) }
        )
        HomeAdsSlider(
            ads = state.ads
        )
        HomeCard(
            modifier = Modifier.padding(),
            cardColor = DelivaryUserTheme.colors.status.greenAccent,
            text = stringResource(R.string.fast_order),
            image = painterResource(R.drawable.img_fast_order),
            imageHeight = 75.dp,
            imageWidth = 98.dp,
            onCardClicked = { action(HomeContract.Action.FastOrderClicked) }
        )
        HomeCard(
            cardColor = DelivaryUserTheme.colors.status.redAccent,
            text = stringResource(R.string.order_with_ai),
            image = painterResource(R.drawable.img_order_with_ai),
            imageHeight = 71.dp,
            imageWidth = 63.dp,
            onCardClicked = { action(HomeContract.Action.ChatWithAiClicked) }
        )
    }
}

@PreviewAllVariants
@Composable
private fun HomeScreenPreview() = DelivaryUserTheme {
    HomeContent(
        state = HomeContract.State(
            ads = listOf(
                HomeContract.AdUiState(
                    id = "",
                    image = "https://delivery-online.com/uploads/ads/source/12256-80209.webp"
                ),
                HomeContract.AdUiState(
                    id = ""
                ),
                HomeContract.AdUiState(
                    id = ""
                ),
                HomeContract.AdUiState(
                    id = ""
                ),
                HomeContract.AdUiState(
                    id = ""
                )
            )
        ),
        action = {})
}