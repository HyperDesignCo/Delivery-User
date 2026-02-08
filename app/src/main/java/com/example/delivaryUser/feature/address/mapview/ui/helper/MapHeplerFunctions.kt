package com.example.delivaryUser.feature.address.mapview.ui.helper

import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext




fun buildAddressString(address: Address): String {
    val parts = mutableListOf<String>()
    address.getAddressLine(0)?.let { parts.add(it) }
    address.thoroughfare?.let { if (!parts.contains(it)) parts.add(it) }
    address.subLocality?.let { if (!parts.contains(it)) parts.add(it) }
    address.locality?.let { if (!parts.contains(it)) parts.add(it) }
    return parts.joinToString(", ")
}

suspend fun geocodePredictionWithPlaceId(
    placeId: String,
    placesClient: PlacesClient,
    context: Context,
    onResult: (LatLng, String) -> Unit
) {
    withContext(Dispatchers.IO) {
        try {
            val placeFields = listOf(Place.Field.LOCATION, Place.Field.SHORT_FORMATTED_ADDRESS)
            val request = FetchPlaceRequest.newInstance(placeId, placeFields)

            placesClient.fetchPlace(request)
                .addOnSuccessListener { response ->
                    val place = response.place
                    val latLng = place.location
                    val addressName = place.shortFormattedAddress ?: ""

                    if (latLng != null) {
                        onResult(latLng, addressName)
                    }
                }
                .addOnFailureListener { e ->

                }
        } catch (e: Exception) {

        }
    }
}