package com.hyperdesign.delivaryUser.feature.address.mapview.domain.repository

import com.google.android.gms.maps.model.LatLng

interface IMapRepository {
    suspend fun requestCurrentLocation(callback: (LatLng?) -> Unit)
    suspend fun getSavedLocation(): LatLng?
    suspend fun saveLocation(latLng: LatLng)
    suspend fun reverseGeocode(latLng: LatLng): String
    suspend fun isFirstLaunch(): Boolean
    suspend fun setFirstLaunchComplete()
}