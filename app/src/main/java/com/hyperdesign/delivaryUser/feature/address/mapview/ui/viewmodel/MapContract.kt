package com.hyperdesign.delivaryUser.feature.address.mapview.ui.viewmodel

import com.hyperdesign.delivaryUser.service.location.domain.model.CheckLocation
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.net.PlacesClient

interface MapContract {
    sealed interface Action {
        data object RequestLocationPermission : Action
        data object MapLoaded : Action
        data class MapClicked(val latLng: LatLng) : Action
        data class SearchChanged(val query: String) : Action
        data object ClearQueryChanged : Action
        data object ChooseLocationClicked : Action
        data class CameraIdleAtLocation(val latLng: LatLng) : Action
        data class GoogleMapChanged(val googleMap: GoogleMap) : Action
        data class AnimateCameraToLocation(val latLng: LatLng) : Action
        data class ResetLocation(val latLng: LatLng) : Action
        data object RetryLocationClicked : Action
        data class UserLocationAcquired(val latLng: LatLng) : Action
        data class ShowSearchResults(val showResults: Boolean) : Action
        data class UpdatePlacePredictions(val predictions: List<Pair<String, String>>) : Action
        data class SetPlacesClient(val client: PlacesClient?) : Action
        data class SetSessionToken(val token: AutocompleteSessionToken?) : Action
        data class SetCurrentUserLocation(val location: LatLng?) : Action
        data class NoAreaScreenChange(val isShow: Boolean) : Action
        data object OnBackClick: Action
    }

    data class State(
        val isPermissionGranted: Boolean = false,
        val isMapReady: Boolean = false,
        val isLoadingLocation: Boolean = true,
        val searchQuery: String = "",
        val currentLocation: LatLng? = null,
        val targetLocation: LatLng? = null,
        val detectedAddress: String? = null,
        val showNoAreaScreen: Boolean = false,
        val error: String? = null,
        val googleMap: GoogleMap? = null,
        val navigateFrom: String = "",
        val addressId: String = "",
        val showSearchResults: Boolean = false,
        val placePredictions: List<Pair<String, String>> = emptyList(),
        val placesClient: PlacesClient? = null,
        val sessionToken: AutocompleteSessionToken? = null,
        val currentUserLocation: LatLng? = null,
        val checkLocationResponse: CheckLocation? = null
    )


}