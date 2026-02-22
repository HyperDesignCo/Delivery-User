package com.hyperdesign.delivaryUser.service.location.domain

import com.google.android.gms.maps.model.LatLng
import com.hyperdesign.delivaryUser.service.location.data.model.request.CheckLocationRequest
import com.hyperdesign.delivaryUser.service.location.domain.model.CheckLocation
import com.hyperdesign.delivaryUser.service.location.domain.model.Location

interface ILocationRepository {
    suspend fun checkLocation(request: CheckLocationRequest): CheckLocation
    suspend fun saveCheckLocationResponse(checkLocation: Location)
    suspend fun getCheckLocationResponse(): Location
    suspend fun saveLocation(latLng: LatLng)
    suspend fun getSavedLocation(): LatLng?
    suspend fun resolveLocation(latLng: LatLng): LocationResult
}