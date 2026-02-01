package com.example.delivaryUser.feature.address.mapview.ui.view

import android.Manifest
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.bars.topbar.DelivaryUserTopBar
import com.example.delivaryUser.common.ui.components.buttons.DelivaryUserButtonPrimary
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.components.screen.DelivaryUserScreen
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.address.mapview.ui.component.LocationIsNotReadyYet
import com.example.delivaryUser.feature.address.mapview.ui.component.PermissionRequestScreen
import com.example.delivaryUser.feature.address.mapview.ui.component.SearchLocationBar
import com.example.delivaryUser.feature.address.mapview.ui.component.SearchResultsList
import com.example.delivaryUser.feature.address.mapview.ui.component.SelectedLocationCard
import com.example.delivaryUser.feature.address.mapview.ui.helper.geocodePredictionWithPlaceId
import com.example.delivaryUser.feature.address.mapview.ui.viewmodel.MapContract
import com.example.delivaryUser.feature.address.mapview.ui.viewmodel.MapViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import org.koin.androidx.compose.koinViewModel
@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(
    viewModel: MapViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    MapScreenContent(
        state = state, action = viewModel::onActionTrigger
    )
}

@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun MapScreenContent(
    state: MapContract.State, action: (MapContract.Action) -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val permissionState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    var currentLocation by remember { mutableStateOf<LatLng?>(null) }

    val isLocationReady by remember {
        derivedStateOf { currentLocation != null }
    }

    LaunchedEffect(permissionState.allPermissionsGranted) {
        if (permissionState.allPermissionsGranted) {
            val location = fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY, null
            ).await()

            if (location != null) {
                val userLocation = LatLng(location.latitude, location.longitude)
                currentLocation = userLocation

                action(MapContract.Action.SetCurrentUserLocation(userLocation))
                action(MapContract.Action.UserLocationAcquired(userLocation))

            }
        }
    }

    DelivaryUserScreen(
        header = {
            DelivaryUserTopBar(
                onStartIconClicked = {action(MapContract.Action.OnBackClick) },
                content = { Text(stringResource(R.string.add_new_address)) },
            )
        }) {

        if (!permissionState.allPermissionsGranted) {
            PermissionRequestScreen { permissionState.launchMultiplePermissionRequest() }

        } else if (permissionState.allPermissionsGranted && !isLocationReady) {
            LocationIsNotReadyYet()

        } else {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                AndroidView(
                    factory = { context ->
                        MapView(context).apply {
                            onCreate(null)
                            getMapAsync { googleMap ->
                                action(MapContract.Action.GoogleMapChanged(googleMap))
                                action(MapContract.Action.MapLoaded)
                            }
                        }
                    }, modifier = Modifier.fillMaxSize()
                )

                    Icon(
                        painter = painterResource(R.drawable.ic_map_mark),
                        contentDescription = "Location pin",
                        modifier = Modifier.size(28.dp),
                        tint = DelivaryUserTheme.colors.primary
                    )

                Column(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth()
                        .padding(12.dp)
                        .zIndex(10f)
                ) {
                    SearchLocationBar(query = state.searchQuery, onQueryChange = { newQuery ->
                        action(MapContract.Action.SearchChanged(newQuery))
                        if (newQuery.isNotEmpty()) {
                            state.placesClient?.let { client ->
                                val request = FindAutocompletePredictionsRequest.builder()
                                    .setSessionToken(state.sessionToken).setQuery(newQuery).build()

                                client.findAutocompletePredictions(request)
                                    .addOnSuccessListener { response ->
                                        val predictions = response.autocompletePredictions.map {
                                            it.placeId to it.getFullText(null).toString()
                                        }
                                        action(MapContract.Action.UpdatePlacePredictions(predictions))
                                        action(MapContract.Action.ShowSearchResults(true))
                                    }.addOnFailureListener { e ->

                                    }
                            }
                        } else {
                            action(MapContract.Action.ShowSearchResults(false))
                            action(MapContract.Action.UpdatePlacePredictions(emptyList()))
                        }
                    }, onClear = {
                        action(MapContract.Action.ClearQueryChanged)
                        action(MapContract.Action.ShowSearchResults(false))
                        action(MapContract.Action.UpdatePlacePredictions(emptyList()))
                    })

                    if (state.showSearchResults && state.placePredictions.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        SearchResultsList(
                            predictions = state.placePredictions.map { it.second },
                            onPredictionClick = { selectedAddress ->
                                action(MapContract.Action.SearchChanged(selectedAddress))
                                action(MapContract.Action.ShowSearchResults(false))

                                val selectedPlaceId = state.placePredictions.find {
                                    it.second == selectedAddress
                                }?.first
                                if (selectedPlaceId != null && state.placesClient != null) {
                                    scope.launch {
                                        geocodePredictionWithPlaceId(
                                            selectedPlaceId, state.placesClient, context
                                        ) { latLng, address ->
                                            action(MapContract.Action.AnimateCameraToLocation(latLng))
                                            action(MapContract.Action.CameraIdleAtLocation(latLng))
                                        }
                                    }
                                }

                                action(MapContract.Action.UpdatePlacePredictions(emptyList()))
                            })
                    }
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 30.dp)
                        .zIndex(5f)
                ) {
                    state.detectedAddress?.let { address ->
                        SelectedLocationCard(address = address)

                    }

                    Spacer(Modifier.height(16.dp))

                    DelivaryUserButtonPrimary(
                        label = stringResource(R.string.choose_location),
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { action(MapContract.Action.ChooseLocationClicked) })

                    Spacer(Modifier.height(20.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_reset_location),
                            contentDescription = "Reset Location Button",
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {
                                    currentLocation?.let { location ->
                                        action(MapContract.Action.ResetLocation(location))
                                    }
                                }
                        )
                    }
                }
            }
        }
    }
}

@Composable
@PreviewAllVariants
private fun MapScreenPreview() = DelivaryUserTheme {
    MapScreenContent(state = MapContract.State(), action = {})
}