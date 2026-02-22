package com.example.delivaryUser.service.location.domain.repository

import com.example.delivaryUser.service.location.domain.LocationResult
import com.example.delivaryUser.service.location.data.model.request.CheckLocationRequest
import com.example.delivaryUser.service.location.domain.model.CheckLocation
import com.example.delivaryUser.service.location.domain.model.Location
import com.google.android.gms.maps.model.LatLng

interface ILocationRepository {
    suspend fun checkLocation(request: CheckLocationRequest): CheckLocation

    suspend fun saveCheckLocationResponse(checkLocation: Location)

    suspend fun getCheckLocationResponse(): Location
    suspend fun saveLocation(latLng: LatLng)
    suspend fun getSavedLocation(): LatLng?
    suspend fun resolveLocation(latLng: LatLng): LocationResult
}

