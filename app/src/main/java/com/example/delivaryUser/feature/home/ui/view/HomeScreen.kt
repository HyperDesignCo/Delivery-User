package com.example.delivaryUser.feature.home.ui.view

import android.Manifest
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.bars.topbar.DelivaryUserTopBar
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.components.screen.DelivaryUserScreen
import com.example.delivaryUser.common.ui.extension.clickableWithNoRipple
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.home.ui.components.HomeAdsSlider
import com.example.delivaryUser.feature.home.ui.components.HomeCard
import com.example.delivaryUser.feature.home.ui.components.HomeLocation
import com.example.delivaryUser.feature.home.ui.viewmodel.HomeContract
import com.example.delivaryUser.feature.home.ui.viewmodel.HomeViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.tasks.await
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeContent(state = state, action = viewModel::onActionTrigger)
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeContent(state: HomeContract.State, action: (HomeContract.Action) -> Unit) {

    val context = LocalContext.current
    val permissionState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    var permissionRequested by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (!permissionState.allPermissionsGranted && !permissionRequested) {
            permissionState.launchMultiplePermissionRequest()
            permissionRequested = true
        }
    }

    LaunchedEffect(permissionState.allPermissionsGranted) {
        if (permissionState.allPermissionsGranted) {
            try {
                val location = fusedLocationClient.getCurrentLocation(
                    Priority.PRIORITY_HIGH_ACCURACY,
                    null
                ).await()
                if (location != null) {
                    val userLocation = LatLng(location.latitude, location.longitude)
                    action(HomeContract.Action.OnChangeLocation(userLocation))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    DelivaryUserScreen(
        header = {
            DelivaryUserTopBar({})
        },
        contentVerticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        scrollState = rememberScrollState(),
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
        if (state.isButtonsVisible) {
            OrderTypeItem(
                label = stringResource(R.string.new_order),
                icon = painterResource(R.drawable.img_new_order),
                onClick = { action(HomeContract.Action.OnNewOrderClicked) }
            )
            OrderTypeItem(
                label = stringResource(R.string.point_to_point),
                icon = painterResource(R.drawable.img_point_to_point),
                onClick = { action(HomeContract.Action.OnPointToPointClicked) }
            )
        }
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

@Composable
private fun OrderTypeItem(
    label: String,
    icon: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        DelivaryUserTheme.colors.status.greenAccent,
                        DelivaryUserTheme.colors.status.darkGreen
                    )
                ),
                shape = RoundedCornerShape(20.dp)
            )
            .clickableWithNoRipple { onClick() }
            .padding(vertical = 8.dp, horizontal = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = label,
            style = DelivaryUserTheme.typography.headline.large,
            color = DelivaryUserTheme.colors.background.surfaceHigh
        )
        Image(
            modifier = Modifier.size(34.dp),
            painter = icon,
            contentDescription = null
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