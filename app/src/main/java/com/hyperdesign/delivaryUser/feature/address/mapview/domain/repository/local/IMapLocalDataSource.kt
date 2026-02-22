package com.hyperdesign.delivaryUser.feature.address.mapview.domain.repository.local


import com.hyperdesign.delivaryUser.service.location.domain.model.Location
import com.google.android.gms.maps.model.LatLng

interface IMapLocalDataSource {

    suspend fun saveLocation(latLng: LatLng)

    suspend fun getSavedLocation(): LatLng?

    suspend fun saveLocationResponse(locationResponse: Location)

    suspend fun getSavedLocationResponse(): Location

    suspend fun setFirstLaunchComplete()
    suspend fun isFirstLaunch(): Boolean
    suspend fun clearMapData()
}