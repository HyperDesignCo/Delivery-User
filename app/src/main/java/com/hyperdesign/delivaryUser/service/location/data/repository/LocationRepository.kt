package com.hyperdesign.delivaryUser.service.location.data.repository

import com.google.android.gms.maps.model.LatLng
import com.hyperdesign.delivaryUser.service.location.data.mapper.CheckLocationMapper
import com.hyperdesign.delivaryUser.service.location.data.mapper.LocationMapper
import com.hyperdesign.delivaryUser.service.location.data.model.request.CheckLocationRequest
import com.hyperdesign.delivaryUser.service.location.domain.ILocationRepository
import com.hyperdesign.delivaryUser.service.location.domain.LocationResult
import com.hyperdesign.delivaryUser.service.location.domain.model.CheckLocation
import com.hyperdesign.delivaryUser.service.location.domain.model.Location
import com.hyperdesign.delivaryUser.service.location.domain.repository.local.ILocationLocalDataSource
import com.hyperdesign.delivaryUser.service.location.domain.repository.remote.ILocationRemoteDataSource

class LocationRepository(
    private val remote: ILocationRemoteDataSource,
    private val local: ILocationLocalDataSource,
) : ILocationRepository {

    override suspend fun checkLocation(request: CheckLocationRequest): CheckLocation =
        CheckLocationMapper.dtoToDomain(remote.checkLocation(request))

    override suspend fun saveCheckLocationResponse(location: Location) =
        local.saveCheckLocationResponse(LocationMapper.domainToEntity(location))

    override suspend fun getCheckLocationResponse(): Location =
        LocationMapper.entityToDomain(local.getCheckLocationResponse())

    override suspend fun saveLocation(latLng: LatLng) =
        local.saveLocation(latLng)

    override suspend fun getSavedLocation(): LatLng? =
        local.getSavedLocation()

    override suspend fun resolveLocation(latLng: LatLng): LocationResult {
        val savedLatLng = local.getSavedLocation()

        if (savedLatLng != null && isSameLocation(savedLatLng, latLng)) {
            return LocationResult.SameLocation
        }

        val savedLocation = getCheckLocationResponse()
        if (savedLocation.currentRegion.isNotEmpty()) {
            val displayLocation = buildDisplayLocation(
                regionName = savedLocation.currentRegionName,
                areaName = savedLocation.currentAreaName
            )
            return LocationResult.LocationFound(
                displayLocation = displayLocation,
                savedLatLng = latLng
            )
        }
        val request = CheckLocationRequest(
            latitude = latLng.latitude.toString(),
            longitude = latLng.longitude.toString()
        )
        val checkLocation = checkLocation(request)
        val currentRegion = checkLocation.data.currentRegion
        val currentArea = checkLocation.data.currentArea

        return if (currentRegion.isEmpty() || currentArea.isEmpty()) {
            saveLocation(latLng)
            LocationResult.OutOfZone(
                latitude = latLng.latitude,
                longitude = latLng.longitude
            )
        } else {
            saveLocation(latLng)
            saveCheckLocationResponse(checkLocation.data)
            val displayLocation = buildDisplayLocation(
                regionName = checkLocation.data.currentRegionName,
                areaName = checkLocation.data.currentAreaName
            )
            LocationResult.LocationFound(
                displayLocation = displayLocation,
                savedLatLng = latLng
            )
        }
    }

    private fun isSameLocation(savedLatLng: LatLng, targetLocation: LatLng): Boolean {
        val tolerance = 0.0002
        return Math.abs(savedLatLng.latitude - targetLocation.latitude) < tolerance &&
                Math.abs(savedLatLng.longitude - targetLocation.longitude) < tolerance
    }

    private fun buildDisplayLocation(regionName: String, areaName: String): String {
        return "$regionName,$areaName"
    }
}