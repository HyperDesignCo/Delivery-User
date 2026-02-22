package com.hyperdesign.delivaryUser.feature.address.mapview.data.repository.local

import com.hyperdesign.delivaryUser.common.data.repository.local.LocalDataSourceEnum
import com.hyperdesign.delivaryUser.common.domain.local.ILocalDataSourceProvider
import com.hyperdesign.delivaryUser.feature.address.mapview.domain.repository.local.IMapLocalDataSource
import com.hyperdesign.delivaryUser.service.location.domain.model.Location
import com.google.android.gms.maps.model.LatLng
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class MapLocalDataSource(
    private val localProvider: ILocalDataSourceProvider, private val json: Json
) : IMapLocalDataSource {

    @Serializable
    private data class LatLngData(
        val latitude: Double = 0.0, val longitude: Double = 0.0
    )

    override suspend fun saveLocation(latLng: LatLng) {
        val latLngData = LatLngData(
            latitude = latLng.latitude, longitude = latLng.longitude
        )
        val jsonString = json.encodeToString(LatLngData.serializer(), latLngData)
        localProvider.save(
            key = LocalDataSourceEnum.SAVED_LOCATION, value = jsonString, type = String::class.java
        )
    }

    override suspend fun getSavedLocation(): LatLng? {

        val jsonString = localProvider.read(
            key = LocalDataSourceEnum.SAVED_LOCATION, defaultValue = "", type = String::class.java
        )
        if (jsonString.isEmpty()) return null

        val latLngData = json.decodeFromString(LatLngData.serializer(), jsonString)
        return LatLng(latLngData.latitude, latLngData.longitude)

    }



    override suspend fun setFirstLaunchComplete() {

        localProvider.save(
            key = LocalDataSourceEnum.FIRST_LAUNCH_COMPLETE,
            value = true,
            type = Boolean::class.java
        )

    }

    override suspend fun isFirstLaunch(): Boolean {

        val isComplete = localProvider.read(
            key = LocalDataSourceEnum.FIRST_LAUNCH_COMPLETE,
            defaultValue = false,
            type = Boolean::class.java
        )

        return isComplete


    }

    override suspend fun clearMapData() {

        localProvider.save(
            key = LocalDataSourceEnum.SAVED_LOCATION, value = "", type = String::class.java
        )

    }
}
