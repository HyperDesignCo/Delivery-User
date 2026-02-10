package com.example.delivaryUser.feature.trackorder.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.buttons.DelivaryUserButtonPrimary
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

@Composable
fun MapSection(
    onMapClicked: () -> Unit,
    modifier: Modifier = Modifier,
    driverLocation: LatLng = LatLng(30.0444, 31.2357),
) {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }

    DisposableEffect(Unit) {
        mapView.onCreate(null)
        mapView.onResume()
        onDispose {
            mapView.onPause()
            mapView.onDestroy()
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        AndroidView(
            factory = {
                mapView.apply {
                    getMapAsync { googleMap ->
                        googleMap.uiSettings.apply {
                            isZoomControlsEnabled = true
                            isScrollGesturesEnabled = true
                            isZoomGesturesEnabled = true
                            isRotateGesturesEnabled = true
                            isTiltGesturesEnabled = true
                            isMapToolbarEnabled = true
                        }
                        googleMap.moveCamera(
                            CameraUpdateFactory.newLatLngZoom(driverLocation, 15f)
                        )
                        googleMap.addMarker(
                            MarkerOptions()
                                .position(driverLocation)
                                .title(context.getString(R.string.driver_location))
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@PreviewAllVariants
@Composable
private fun MapSectionPreview() = DelivaryUserTheme {
    MapSection(onMapClicked = {})
}