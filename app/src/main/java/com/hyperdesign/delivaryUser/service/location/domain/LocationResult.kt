package com.hyperdesign.delivaryUser.service.location.domain

import com.google.android.gms.maps.model.LatLng

sealed class LocationResult {
    data class LocationFound(
        val displayLocation: String,
        val savedLatLng: LatLng,
    ) : LocationResult()

    data class OutOfZone(val latitude: Double, val longitude: Double) : LocationResult()
    data object SameLocation : LocationResult()
}