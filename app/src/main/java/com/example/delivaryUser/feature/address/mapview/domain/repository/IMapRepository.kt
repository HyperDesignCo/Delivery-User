package com.example.delivaryUser.feature.address.mapview.domain.repository

import com.example.delivaryUser.service.location.domain.model.Location
import com.google.android.gms.maps.model.LatLng

interface IMapRepository {

    suspend fun requestCurrentLocation(callback: (LatLng?) -> Unit)

    suspend fun getSavedLocation(): LatLng?

    suspend fun saveLocation(latLng: LatLng)

    suspend fun reverseGeocode(latLng: LatLng): String

    suspend fun checkLocationDelivery(
        latitude: String,
        longitude: String,
        callback: (deliveryStatus: String, currentRestaurantBranch: String?, areaId: String) -> Unit
    )

    suspend fun isFirstLaunch(): Boolean

    suspend fun setFirstLaunchComplete()
    suspend fun saveLocationResponse(locationResponse: Location)
    suspend fun getSavedLocationResponse(): Location
}