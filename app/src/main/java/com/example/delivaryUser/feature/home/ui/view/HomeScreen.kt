package com.example.delivaryUser.feature.home.ui.view

import android.Manifest
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.delivaryUser.common.ui.extension.clickableWithNoRipple
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.home.ui.components.HomeAdsSlider
import com.example.delivaryUser.feature.home.ui.components.HomeCard
import com.example.delivaryUser.feature.home.ui.components.HomeLocation
import com.example.delivaryUser.feature.home.ui.components.HomeTrackOrderItem
import com.example.delivaryUser.feature.home.ui.viewmodel.HomeContract
import com.example.delivaryUser.feature.home.ui.viewmodel.HomeViewModel
import com.example.delivaryUser.feature.orders.base.ui.asString
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.tasks.await
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {

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
                    viewModel.onActionTrigger(HomeContract.Action.OnChangeLocation(userLocation))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeContent(state = state, action = viewModel::onActionTrigger)
}

@Composable
fun HomeContent(state: HomeContract.State, action: (HomeContract.Action) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = DelivaryUserTheme.colors.background.surfaceHigh)
            .navigationBarsPadding()
            .statusBarsPadding(),
        contentPadding = PaddingValues(
            bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() + 24.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        stickyHeader {
            DelivaryUserTopBar(startIcon = null)
            HomeLocation(
                location = state.location,
                onLocationClicked = { action(HomeContract.Action.OnLocationClicked(state.location)) },
                onAddLocationClicked = { action(HomeContract.Action.OnAddLocationClicked) }
            )
        }
        item {
            HomeAdsSlider(
                modifier = Modifier.padding(horizontal = 16.dp),
                ads = state.ads
            )
        }
        if(state.trackOrders.isNotEmpty())
        items(state.trackOrders.size, key = { state.trackOrders[it].orderId }) { index ->
            HomeTrackOrderItem(
                modifier = Modifier.padding(horizontal = 16.dp),
                orderStatus = state.trackOrders[index].orderStatus.asString(),
                orderId = state.trackOrders[index].orderId,
                image = state.trackOrders[index].image,
                userName = state.trackOrders[index].deliveryName,
                onTrackOrderClicked = { action(HomeContract.Action.OnTrackOrderClicked(state.trackOrders[index].orderId.toInt())) }
            )
        }
        item {
            HomeCard(
                modifier = Modifier.padding(horizontal = 16.dp),
                cardColor = DelivaryUserTheme.colors.status.greenAccent,
                text = stringResource(R.string.fast_order),
                image = painterResource(R.drawable.img_fast_order),
                imageHeight = 75.dp,
                imageWidth = 98.dp,
                onCardClicked = { action(HomeContract.Action.FastOrderClicked) }
            )
        }
        item {
            if (state.isButtonsVisible) {
                OrderTypeItem(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    label = stringResource(R.string.new_order),
                    icon = painterResource(R.drawable.img_new_order),
                    onClick = { action(HomeContract.Action.OnNewOrderClicked) }
                )
                OrderTypeItem(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),
                    label = stringResource(R.string.point_to_point),
                    icon = painterResource(R.drawable.img_point_to_point),
                    onClick = { action(HomeContract.Action.OnPointToPointClicked) }
                )
            }
        }
        item {
            HomeCard(
                modifier = Modifier.padding(horizontal = 16.dp),
                cardColor = DelivaryUserTheme.colors.status.redAccent,
                text = stringResource(R.string.order_with_ai),
                image = painterResource(R.drawable.img_order_with_ai),
                imageHeight = 71.dp,
                imageWidth = 63.dp,
                onCardClicked = { action(HomeContract.Action.ChatWithAiClicked) }
            )
        }
        item { Spacer(modifier = Modifier.width(24.dp)) }
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