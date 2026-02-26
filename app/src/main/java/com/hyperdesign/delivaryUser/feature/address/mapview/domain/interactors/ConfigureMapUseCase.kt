package com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase

data class ConfigureMapParams(
    val googleMap: GoogleMap,
    val savedLocation: LatLng?,
    val currentLocation: LatLng?,
    val defaultZoom: Float,
)
class ConfigureMapUseCase(
    private val validateLocationUseCase: ValidateLocationUseCase,
) : BaseUseCase<Resource<LatLng?>, ConfigureMapParams>() {

    override suspend fun invoke(body: ConfigureMapParams): Resource<LatLng?> =
        nonFlowExecute {
            val googleMap = body.googleMap

            googleMap.uiSettings.apply {
                isMyLocationButtonEnabled = true
                isZoomControlsEnabled = false
                isCompassEnabled = true
            }
            googleMap.isMyLocationEnabled = true

            val locationToUse = when {
                body.savedLocation != null && isValid(body.savedLocation) -> body.savedLocation
                body.currentLocation != null && isValid(body.currentLocation) -> body.currentLocation
                else -> null
            }

            if (locationToUse != null) {
                googleMap.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(locationToUse, body.defaultZoom)
                )
            } else {
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(0.0, 0.0), 2f))
            }

            locationToUse
        }

    private suspend fun isValid(latLng: LatLng): Boolean {
        val result = validateLocationUseCase.invoke(latLng)
        return result is Resource.Success && result.model == true
    }
}
