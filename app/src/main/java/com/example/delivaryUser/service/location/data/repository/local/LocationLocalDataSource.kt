package com.example.delivaryUser.service.location.data.repository.local

import com.example.delivaryUser.common.data.repository.local.LocalDataSourceEnum
import com.example.delivaryUser.common.domain.local.ILocalDataSourceProvider
import com.example.delivaryUser.service.location.data.entity.LocationEntity
import com.example.delivaryUser.service.location.domain.repository.local.ILocationLocalDataSource
import com.google.android.gms.maps.model.LatLng
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class LocationLocalDataSource(
    private val provider: ILocalDataSourceProvider,
    private val json: Json
) : ILocationLocalDataSource {

    override suspend fun saveCheckLocationResponse(checkLocation: LocationEntity) {
        provider.save(
            LocalDataSourceEnum.CHECK_LOCATION_RESPONSE,
            value = json.encodeToString(checkLocation),
            type = String::class.java
        )
    }

    override suspend fun getCheckLocationResponse(): LocationEntity {
        val jsonString = provider.read(
            key = LocalDataSourceEnum.CHECK_LOCATION_RESPONSE,
            defaultValue = "",
            type = String::class.java
        )
        return if (jsonString.isNotEmpty()) {
            json.decodeFromString(
                deserializer = LocationEntity.serializer(),
                string = jsonString
            )
        } else {
            LocationEntity()
        }
    }

    @Serializable
    private data class LatLngData(
        val latitude: Double = 0.0, val longitude: Double = 0.0
    )

    override suspend fun saveLocation(latLng: LatLng) {
        val latLngData = LatLngData(
            latitude = latLng.latitude, longitude = latLng.longitude
        )
        val jsonString = json.encodeToString(LatLngData.serializer(), latLngData)
        provider.save(
            key = LocalDataSourceEnum.SAVED_LOCATION, value = jsonString, type = String::class.java
        )
    }

    override suspend fun getSavedLocation(): LatLng? {

        val jsonString = provider.read(
            key = LocalDataSourceEnum.SAVED_LOCATION, defaultValue = "", type = String::class.java
        )
        if (jsonString.isEmpty()) return null

        val latLngData = json.decodeFromString(LatLngData.serializer(), jsonString)
        return LatLng(latLngData.latitude, latLngData.longitude)

    }
}