package com.example.delivaryUser.feature.address.mapview.data.repository

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.util.Log
import com.example.delivaryUser.feature.address.mapview.domain.repository.IMapRepository
import com.example.delivaryUser.feature.address.mapview.domain.repository.local.IMapLocalDataSource
import com.example.delivaryUser.feature.address.mapview.ui.helper.buildAddressString
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.Locale


class MapRepository(
    private val context: Context,
    private val local: IMapLocalDataSource,
    private val fusedLocationProviderClient: FusedLocationProviderClient
) : IMapRepository {


    private val arabicLocale = Locale("ar", "EG")
    private val geocoder by lazy { Geocoder(context, arabicLocale) }
    private val geocoderEnglish by lazy { Geocoder(context, Locale.ENGLISH) }

    override suspend fun requestCurrentLocation(callback: (LatLng?) -> Unit) {

        val location = fusedLocationProviderClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            null
        ).await()

        val latLng = if (location != null) {
            LatLng(location.latitude, location.longitude)
        } else {
            null
        }
        callback(latLng)

    }

    override suspend fun getSavedLocation(): LatLng? {

        return local.getSavedLocation()

    }

    override suspend fun saveLocation(latLng: LatLng) {

        local.saveLocation(latLng)

    }

    override suspend fun reverseGeocode(latLng: LatLng): String {
        return withContext(Dispatchers.IO) {

            var addresses = getAddresses(geocoder, latLng)

            if (addresses.isEmpty()) {
                addresses = getAddresses(geocoderEnglish, latLng)
            }

            if (addresses.isNotEmpty()) {
                val address = addresses[0]

                val formattedAddress = buildAddressString(address)

                return@withContext formattedAddress
            } else {
                Log.w("MapRepository", ">>> No addresses found from Geocoder")
                return@withContext "الموقع: ${latLng.latitude}, ${latLng.longitude}"
            }

        }
    }


    private fun getAddresses(geocoder: Geocoder, latLng: LatLng): List<Address> {
        return geocoder.getFromLocation(latLng.latitude, latLng.longitude, 5) ?: emptyList()

    }

    override suspend fun checkLocationDelivery(
        latitude: String,
        longitude: String,
        callback: (deliveryStatus: String, currentRestaurantBranch: String?, areaId: String) -> Unit
    ) {
        // TODO: Implement when API is ready
    }

    override suspend fun isFirstLaunch(): Boolean {
        return local.isFirstLaunch()

    }

    override suspend fun setFirstLaunchComplete() {

        local.setFirstLaunchComplete()

    }
}
