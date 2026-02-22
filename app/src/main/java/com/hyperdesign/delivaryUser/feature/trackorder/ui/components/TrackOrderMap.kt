package com.hyperdesign.delivaryUser.feature.trackorder.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.hyperdesign.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.hyperdesign.delivaryUser.feature.trackorder.ui.utilies.drawableToBitmap
import com.hyperdesign.delivaryUser.feature.trackorder.ui.viewmodel.TrackOrderContract
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

@Composable
fun MapSection(
    modifier: Modifier = Modifier,
    client: TrackOrderContract.Client,
    delivery: TrackOrderContract.DeliveryState,
    orderType: String,
    isDataReady: Boolean
) {
    if (!isDataReady) return

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

                        val boundsBuilder = LatLngBounds.Builder()
                        var hasValidLocation = false


                        val driverMarkerBitmap = context.drawableToBitmap(
                            R.drawable.img_delivery_driver_location
                        )
                        googleMap.addMarker(
                            MarkerOptions().position(
                                LatLng(
                                    delivery.latitude.toDouble(), delivery.longitude.toDouble()
                                )
                            ).title(context.getString(R.string.driver_location))
                                .icon(BitmapDescriptorFactory.fromBitmap(driverMarkerBitmap))
                        )
                        boundsBuilder.include(
                            LatLng(
                                delivery.latitude.toDouble(), delivery.longitude.toDouble()
                            )
                        )
                        hasValidLocation = true


                        when (orderType) {
                            "1" -> {

                                val homeMarkerBitmap = context.drawableToBitmap(
                                    R.drawable.img_home_user_location
                                )
                                googleMap.addMarker(
                                    MarkerOptions().position(
                                        LatLng(
                                            client.startLatitude.toDouble(),
                                            client.startLongitude.toDouble()
                                        )
                                    ).title(context.getString(R.string.home_location)).icon(
                                        BitmapDescriptorFactory.fromBitmap(
                                            homeMarkerBitmap
                                        )
                                    )
                                )
                                boundsBuilder.include(
                                    LatLng(
                                        client.startLatitude.toDouble(),
                                        client.startLongitude.toDouble()
                                    )
                                )
                                hasValidLocation = true

                            }

                            "2" -> {

                                val firstPointMarkerBitmap = context.drawableToBitmap(
                                    R.drawable.img_first_point_location
                                )
                                googleMap.addMarker(
                                    MarkerOptions().position(
                                        LatLng(
                                            client.startLatitude.toDouble(),
                                            client.startLongitude.toDouble()
                                        )
                                    ).title(context.getString(R.string.first_point)).icon(
                                        BitmapDescriptorFactory.fromBitmap(
                                            firstPointMarkerBitmap
                                        )
                                    )
                                )
                                boundsBuilder.include(
                                    LatLng(
                                        client.startLatitude.toDouble(),
                                        client.startLongitude.toDouble()
                                    )
                                )
                                hasValidLocation = true


                                val secondPointMarkerBitmap = context.drawableToBitmap(
                                    R.drawable.img_second_point_location
                                )
                                googleMap.addMarker(
                                    MarkerOptions().position(
                                        LatLng(
                                            client.endLatitude.toDouble(),
                                            client.endLongitude.toDouble()
                                        )
                                    ).title(context.getString(R.string.second_point)).icon(
                                        BitmapDescriptorFactory.fromBitmap(
                                            secondPointMarkerBitmap
                                        )
                                    )
                                )
                                boundsBuilder.include(
                                    LatLng(
                                        client.endLatitude.toDouble(),
                                        client.endLongitude.toDouble()
                                    )
                                )
                                hasValidLocation = true
                            }

                        }

                        if (hasValidLocation) {
                            val bounds = boundsBuilder.build()
                            val padding = 150
                            googleMap.animateCamera(
                                CameraUpdateFactory.newLatLngBounds(bounds, padding)
                            )

                        } else {
                            val defaultLocation = LatLng(30.0444, 31.2357)
                            googleMap.moveCamera(
                                CameraUpdateFactory.newLatLngZoom(defaultLocation, 13f)
                            )
                        }
                    }
                }
            }, modifier = Modifier.fillMaxSize()
        )
    }
}

@PreviewAllVariants
@Composable
private fun MapSectionPreview() = DelivaryUserTheme {
    MapSection(
        delivery = TrackOrderContract.DeliveryState(),
        client = TrackOrderContract.Client(),
        isDataReady = true,
        orderType = "2"
    )
}