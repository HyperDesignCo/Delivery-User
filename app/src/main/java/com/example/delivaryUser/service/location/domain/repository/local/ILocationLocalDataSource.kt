package com.example.delivaryUser.service.location.domain.repository.local

import com.example.delivaryUser.service.location.data.entity.LocationEntity
import com.google.android.gms.maps.model.LatLng

interface ILocationLocalDataSource {

    suspend fun saveCheckLocationResponse(checkLocation: LocationEntity)

    suspend fun getCheckLocationResponse(): LocationEntity

    suspend fun saveLocation(latLng: LatLng)
    suspend fun getSavedLocation(): LatLng?
}