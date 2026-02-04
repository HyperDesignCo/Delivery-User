package com.example.delivaryUser.feature.address.mapview.ui.viewmodel

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.delivaryUser.BuildConfig
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.extension.UIText
import com.example.delivaryUser.common.ui.loading.ILoadingEvent
import com.example.delivaryUser.common.ui.message.IMessageEvent
import com.example.delivaryUser.common.ui.navigation.IMainGraph
import com.example.delivaryUser.common.ui.navigation.IOrderGraph
import com.example.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.example.delivaryUser.feature.address.mapview.domain.usecase.GetCurrentLocationUseCase
import com.example.delivaryUser.feature.address.mapview.domain.usecase.GetSavedLocationUseCase
import com.example.delivaryUser.feature.address.mapview.domain.usecase.ReverseGeocodeUseCase
import com.example.delivaryUser.feature.address.mapview.domain.usecase.SaveLocationResponseUseCase
import com.example.delivaryUser.feature.address.mapview.domain.usecase.SaveLocationUseCase
import com.example.delivaryUser.feature.pointtopoint.ui.components.AddressType
import com.example.delivaryUser.service.location.data.model.request.CheckLocationRequest
import com.example.delivaryUser.service.location.domain.interactors.CheckLocationUseCase
import com.example.delivaryUser.service.location.domain.model.CheckLocation
import com.example.delivaryUser.service.location.domain.model.Location
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MapViewModel(
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val reverseGeocodeUseCase: ReverseGeocodeUseCase,
    private val saveLocationUseCase: SaveLocationUseCase,
    private val savedLocationResponseUseCase: SaveLocationResponseUseCase,
    private val getSavedLocationUseCase: GetSavedLocationUseCase,
    private val checkLocationUseCase: CheckLocationUseCase,
    private val context: Context,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<MapContract.State, MapContract.Action>(
    state = MapContract.State()
) {

    private val route = savedStateHandle.toRoute<IOrderGraph.Map>()

    private val defaultZoom = 15f
    private var savedLocationToApply: LatLng? = null

    init {
        getSavedLocation()
        initializePlacesApi()
    }

    override fun onActionTrigger(action: MapContract.Action) {
        when (action) {
            is MapContract.Action.RequestLocationPermission -> requestLocationPermission()
            is MapContract.Action.MapLoaded -> mapLoaded()
            is MapContract.Action.MapClicked -> mapClicked(action.latLng)
            is MapContract.Action.ChooseLocationClicked -> chooseLocationClicked()
            is MapContract.Action.CameraIdleAtLocation -> cameraIdleAtLocation(action.latLng)
            is MapContract.Action.RetryLocationClicked -> retryLocationClicked()
            is MapContract.Action.ClearQueryChanged -> clearQuery()
            is MapContract.Action.SearchChanged -> searchQuery(action.query)
            is MapContract.Action.GoogleMapChanged -> googleMapChanged(action.googleMap)
            is MapContract.Action.AnimateCameraToLocation -> animateCameraToLocation(action.latLng)
            is MapContract.Action.ResetLocation -> resetMapToUserLocation(action.latLng)
            is MapContract.Action.UserLocationAcquired -> userLocationAcquired(action.latLng)
            is MapContract.Action.ShowSearchResults -> showResults(action.showResults)
            is MapContract.Action.UpdatePlacePredictions -> updatePlacePredictions(action.predictions)
            is MapContract.Action.SetPlacesClient -> setPlacesClient(action.client)
            is MapContract.Action.SetSessionToken -> setSessionToken(action.token)
            is MapContract.Action.SetCurrentUserLocation -> setCurrentUserLocation(action.location)
            is MapContract.Action.NoAreaScreenChange -> changeNowAreaAvailable(action.isShow)
            MapContract.Action.OnBackClick -> onBackClick()
        }
    }

    private fun onBackClick() {
        viewModelScope.launch(Dispatchers.IO) {
            fireNavigateUp()
        }
    }

    private fun changeNowAreaAvailable(show: Boolean) {
        updateState {
            copy(showNoAreaScreen = show)
        }
    }

    private fun getSavedLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            getSavedLocationUseCase.invoke(Unit).collectResource(
                onSuccess = { savedLatLng ->
                    if (savedLatLng != null && isValidLocation(savedLatLng)) {
                        savedLocationToApply = savedLatLng
                        updateState {
                            copy(
                                currentLocation = savedLatLng,
                                targetLocation = savedLatLng
                            )
                        }
                    } else {
                        fetchCurrentUserLocation()
                    }
                }
            )
        }
    }

    private fun fetchCurrentUserLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            getCurrentLocationUseCase.invoke(body = Unit).collectResource(
                onSuccess = { location ->
                    location?.let { userLocationAcquired(it) }
                },
                onLoading = {
                    updateState { copy(isLoadingLocation = it) }
                },
            )
        }
    }

    private fun initializePlacesApi() {
        viewModelScope.launch {
            if (!Places.isInitialized()) {
                Places.initialize(context, BuildConfig.MAPS_API_KEY)
            }
            val placesClient = Places.createClient(context)
            val sessionToken = AutocompleteSessionToken.newInstance()

            updateState {
                copy(
                    placesClient = placesClient,
                    sessionToken = sessionToken
                )
            }
        }
    }

    private fun updatePlacePredictions(predictions: List<Pair<String, String>>) {
        updateState { copy(placePredictions = predictions) }
    }

    private fun setPlacesClient(client: PlacesClient?) {
        updateState { copy(placesClient = client) }
    }

    private fun setSessionToken(token: AutocompleteSessionToken?) {
        updateState { copy(sessionToken = token) }
    }

    private fun setCurrentUserLocation(location: LatLng?) {
        updateState { copy(currentLocation = location) }
    }

    private fun showResults(showResults: Boolean) {
        updateState { copy(showSearchResults = showResults) }
    }

    private fun userLocationAcquired(latLng: LatLng) {
        updateState { copy(currentLocation = latLng, targetLocation = latLng) }

        val googleMap = state.value.googleMap
        if (googleMap != null) {
            googleMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(latLng, defaultZoom)
            )
            reverseGeocodeLocation(latLng)
        }
    }

    private fun googleMapChanged(googleMap: GoogleMap) {
        updateState { copy(googleMap = googleMap) }
        configureMap(googleMap)
    }

    private fun configureMap(googleMap: GoogleMap) {
        googleMap.uiSettings.apply {
            isMyLocationButtonEnabled = true
            isZoomControlsEnabled = false
            isCompassEnabled = true
        }

        googleMap.isMyLocationEnabled = true

        val locationToUse = when {
            savedLocationToApply != null && isValidLocation(savedLocationToApply!!) -> {
                savedLocationToApply!!
            }

            state.value.currentLocation != null && isValidLocation(state.value.currentLocation!!) -> {
                state.value.currentLocation!!
            }

            else -> {
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(0.0, 0.0), 2f))
                setupMapListeners(googleMap)
                return
            }
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationToUse, defaultZoom))
        reverseGeocodeLocation(locationToUse)
        setupMapListeners(googleMap)
    }

    private fun setupMapListeners(googleMap: GoogleMap) {
        googleMap.setOnCameraIdleListener {
            val target = googleMap.cameraPosition.target
            updateState { copy(targetLocation = target) }

            viewModelScope.launch {
                delay(300)
                if (isValidLocation(target)) {
                    reverseGeocodeLocation(target)
                }
            }
        }

        googleMap.setOnMapClickListener { latLng ->
            mapClicked(latLng)
        }
    }

    private fun clearQuery() {
        updateState { copy(searchQuery = "") }
    }

    private fun searchQuery(query: String) {
        updateState { copy(searchQuery = query) }
    }

    private fun requestLocationPermission() {
        updateState { copy(isPermissionGranted = true) }
    }

    private fun mapLoaded() {
        updateState { copy(isMapReady = true) }
    }

    private fun mapClicked(latLng: LatLng) {
        updateState { copy(targetLocation = latLng) }

        viewModelScope.launch {
            reverseGeocodeLocation(latLng)
        }
    }

    private fun reverseGeocodeLocation(latLng: LatLng) {
        viewModelScope.launch {
            reverseGeocodeUseCase.invoke(body = latLng).collectResource(
                onSuccess = { address ->
                    updateState { copy(detectedAddress = address) }
                })
        }
    }

    private fun animateCameraToLocation(latLng: LatLng) {
        val googleMap = state.value.googleMap
        if (googleMap != null) {
            googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(latLng, defaultZoom),
                1000,
                null
            )
            viewModelScope.launch {
                delay(1000)
                reverseGeocodeLocation(latLng)
            }
        }
    }

    private fun resetMapToUserLocation(currentUserLocation: LatLng) {
        val googleMap = state.value.googleMap
        if (googleMap != null && isValidLocation(currentUserLocation)) {
            googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(currentUserLocation, defaultZoom),
                1000,
                null
            )
            updateState { copy(targetLocation = currentUserLocation) }
            viewModelScope.launch {
                delay(1000)
                reverseGeocodeLocation(currentUserLocation)
            }
        }
    }

    private fun chooseLocationClicked() {
        val targetLatLng = state.value.targetLocation

        if (targetLatLng == null) {
            fireMessage(
                IMessageEvent.Toast(
                    message = UIText.StringResource(R.string.please_select_a_location_on_the_map)
                )
            )
            return
        }

        // Save the location first
        saveLocation(targetLatLng)
        reverseGeocodeLocation(targetLatLng)

        // Update savedLocationToApply so it will be used when returning to map
        savedLocationToApply = targetLatLng

        val request = CheckLocationRequest(
            latitude = targetLatLng.latitude.toString(),
            longitude = targetLatLng.longitude.toString()
        )
        viewModelScope.launch(Dispatchers.IO) {
            checkLocationUseCase.invoke(request).collectResource(
                onLoading = ::loading,
                onSuccess = { checkLocationResponse ->
                    getResponseOfCheckLocation(
                        checkLocation = checkLocationResponse,
                        targetLatLng = targetLatLng
                    )
                }
            )
        }
    }

    private fun loading(isLoading: Boolean) {
        fireLoading(ILoadingEvent.CircularProgressIndicator(isLoading))
    }

    private fun getResponseOfCheckLocation(checkLocation: CheckLocation, targetLatLng: LatLng) {
        fireMessage(IMessageEvent.Toast(message = UIText.DynamicString(checkLocation.message)))
        updateState {
            copy(checkLocationResponse = checkLocation)
        }
        if (checkLocation.data?.currentRegion.isNullOrEmpty() || checkLocation.data.currentArea.isNullOrEmpty()) {
            fireNavigate(
                IMainGraph.DeliveryOutZone(
                    latitude = targetLatLng.latitude,
                    longitude = targetLatLng.longitude,
                )
            )
        } else {
            when (route.addressType) {
                AddressType.SENDER -> {
                    fireNavigate(IOrderGraph.SaveAddress(addressType = AddressType.SENDER))

                }

                AddressType.RECEIVER -> {
                    fireNavigate(IOrderGraph.SaveAddress(addressType = AddressType.RECEIVER))

                }
            }
            saveLocationResponse(checkLocation.data)
        }
    }

    private fun saveLocationResponse(data: Location) {
        viewModelScope.launch {
            savedLocationResponseUseCase.invoke(body = data)
        }
    }

    private fun cameraIdleAtLocation(latLng: LatLng) {
        if (isValidLocation(latLng)) {
            updateState { copy(targetLocation = latLng) }
            viewModelScope.launch {
                delay(400)
                saveLocation(latLng)
            }
        }
    }

    private fun saveLocation(latLng: LatLng) {
        viewModelScope.launch {
            saveLocationUseCase.invoke(body = latLng).collectResource(
                onSuccess = {
                    reverseGeocodeLocation(latLng)
                }
            )
        }
    }

    private fun retryLocationClicked() {
        updateState { copy(error = null, isLoadingLocation = true) }
        fetchCurrentUserLocation()
    }

    private fun isValidLocation(latLng: LatLng): Boolean {
        return latLng.latitude != 0.0 || latLng.longitude != 0.0
    }
}