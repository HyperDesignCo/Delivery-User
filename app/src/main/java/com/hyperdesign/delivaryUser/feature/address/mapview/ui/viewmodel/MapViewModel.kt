package com.hyperdesign.delivaryUser.feature.address.mapview.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.net.PlacesClient
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.ui.extension.UIText
import com.hyperdesign.delivaryUser.common.ui.loading.ILoadingEvent
import com.hyperdesign.delivaryUser.common.ui.message.IMessageEvent
import com.hyperdesign.delivaryUser.common.ui.message.MessageType
import com.hyperdesign.delivaryUser.common.ui.navigation.IMainGraph
import com.hyperdesign.delivaryUser.common.ui.navigation.IOrderGraph
import com.hyperdesign.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors.ChooseLocationResult
import com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors.ConfigureMapParams
import com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors.ConfigureMapUseCase
import com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors.GetCurrentLocationUseCase
import com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors.GetSavedLocationUseCase
import com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors.HandleChooseLocationParams
import com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors.HandleChooseLocationUseCase
import com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors.InitializePlacesApiUseCase
import com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors.ReverseGeocodeUseCase
import com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors.SaveLocationUseCase
import com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors.ValidateLocationUseCase
import com.hyperdesign.delivaryUser.feature.address.mapview.ui.component.SaveAddressDestinationType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MapViewModel(
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val reverseGeocodeUseCase: ReverseGeocodeUseCase,
    private val saveLocationUseCase: SaveLocationUseCase,
    private val getSavedLocationUseCase: GetSavedLocationUseCase,
    private val initializePlacesApiUseCase: InitializePlacesApiUseCase,
    private val configureMapUseCase: ConfigureMapUseCase,
    private val validateLocationUseCase: ValidateLocationUseCase,
    private val handleChooseLocationUseCase: HandleChooseLocationUseCase,
    savedStateHandle: SavedStateHandle,
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
        viewModelScope.launch(Dispatchers.IO) { fireNavigateUp() }
    }

    private fun getSavedLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            getSavedLocationUseCase.invoke(Unit).collectResource(
                onSuccess = { savedLatLng ->
                    if (savedLatLng != null && isValidLocation(savedLatLng)) {
                        savedLocationToApply = savedLatLng
                        updateState {
                            copy(
                                currentLocation = savedLatLng, targetLocation = savedLatLng
                            )
                        }
                    } else {
                        fetchCurrentUserLocation()
                    }
                })
        }
    }

    private fun fetchCurrentUserLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            getCurrentLocationUseCase.invoke(body = Unit).collectResource(
                onSuccess = { location -> location?.let { userLocationAcquired(it) } },
                onLoading = { updateState { copy(isLoadingLocation = it) } },
            )
        }
    }

    private fun initializePlacesApi() {
        viewModelScope.launch {
            when (val result = initializePlacesApiUseCase.invoke(Unit)) {
                is Resource.Success -> {
                    val (client, token) = result.model
                    updateState { copy(placesClient = client, sessionToken = token) }
                }

                else -> Unit
            }
        }
    }

    private fun googleMapChanged(googleMap: GoogleMap) {
        updateState { copy(googleMap = googleMap) }
        viewModelScope.launch {
            val result = configureMapUseCase.invoke(
                ConfigureMapParams(
                    googleMap = googleMap,
                    savedLocation = savedLocationToApply,
                    currentLocation = state.value.currentLocation,
                    defaultZoom = defaultZoom,
                )
            )
            if (result is Resource.Success) {
                result.model?.let { reverseGeocodeLocation(it) }
            }
            setupMapListeners(googleMap)
        }
    }

    private fun setupMapListeners(googleMap: GoogleMap) {
        googleMap.setOnCameraIdleListener {
            val target = googleMap.cameraPosition.target
            updateState { copy(targetLocation = target) }
            viewModelScope.launch {
                delay(300)
                if (isValidLocation(target)) reverseGeocodeLocation(target)
            }
        }
        googleMap.setOnMapClickListener { latLng -> mapClicked(latLng) }
    }

    private fun userLocationAcquired(latLng: LatLng) {
        updateState { copy(currentLocation = latLng, targetLocation = latLng) }
        val googleMap = state.value.googleMap ?: return
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, defaultZoom))
        reverseGeocodeLocation(latLng)
    }

    private fun mapClicked(latLng: LatLng) {
        updateState { copy(targetLocation = latLng) }
        viewModelScope.launch { reverseGeocodeLocation(latLng) }
    }

    private fun animateCameraToLocation(latLng: LatLng) {
        val googleMap = state.value.googleMap ?: return
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, defaultZoom), 1000, null)
        viewModelScope.launch {
            delay(1000)
            reverseGeocodeLocation(latLng)
        }
    }

    private fun resetMapToUserLocation(currentUserLocation: LatLng) {
        val googleMap = state.value.googleMap ?: return
        viewModelScope.launch {
            if (!isValidLocation(currentUserLocation)) return@launch
            googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(currentUserLocation, defaultZoom), 1000, null
            )
            updateState { copy(targetLocation = currentUserLocation) }
            delay(1000)
            reverseGeocodeLocation(currentUserLocation)
        }
    }

    private fun cameraIdleAtLocation(latLng: LatLng) {
        viewModelScope.launch {
            if (!isValidLocation(latLng)) return@launch
            updateState { copy(targetLocation = latLng) }
            delay(400)
            saveLocation(latLng)
        }
    }

    private fun retryLocationClicked() {
        updateState { copy(error = null, isLoadingLocation = true) }
        fetchCurrentUserLocation()
    }

    private fun chooseLocationClicked() {
        val targetLatLng = state.value.targetLocation ?: run {
            fireMessage(
                IMessageEvent.Snackbar(
                    message = UIText.StringResource(R.string.please_select_a_location_on_the_map),
                    messageType = MessageType.ERROR,
                )
            )
            return
        }

        saveLocation(targetLatLng)
        reverseGeocodeLocation(targetLatLng)
        savedLocationToApply = targetLatLng

        viewModelScope.launch(Dispatchers.IO) {
            handleChooseLocationUseCase.invoke(
                HandleChooseLocationParams(
                    targetLatLng = targetLatLng,
                    addressType = route.addressType,
                )
            ).collectResource(onLoading = { isLoading ->
                fireLoading(ILoadingEvent.CircularProgressIndicator(isLoading))
            }, onSuccess = { result ->
                when (result) {
                    is ChooseLocationResult.CheckLocationLoaded -> updateState {
                        copy(
                            checkLocationResponse = result.checkLocation
                        )
                    }

                    is ChooseLocationResult.OutOfZone -> fireNavigate(
                        IMainGraph.DeliveryOutZone(
                            latitude = result.latitude,
                            longitude = result.longitude,
                        )
                    )

                    is ChooseLocationResult.NavigateToSaveAddress -> fireNavigate(
                        when (result.addressType) {
                            com.hyperdesign.delivaryUser.feature.pointtopoint.ui.components.AddressType.SENDER -> IOrderGraph.SaveAddress(
                                addressType = result.addressType,
                                saveAddressDestinationType = route.saveAddressDestinationType

                            )

                            com.hyperdesign.delivaryUser.feature.pointtopoint.ui.components.AddressType.RECEIVER -> IOrderGraph.SaveAddress(
                                addressType = result.addressType,
                                saveAddressDestinationType = route.saveAddressDestinationType
                            )
                        }
                    )

                    is ChooseLocationResult.Loading -> Unit
                }
            })
        }
    }

    private fun reverseGeocodeLocation(latLng: LatLng) {
        viewModelScope.launch {
            reverseGeocodeUseCase.invoke(body = latLng).collectResource(
                onSuccess = { address -> updateState { copy(detectedAddress = address) } })
        }
    }

    private fun saveLocation(latLng: LatLng) {
        viewModelScope.launch {
            saveLocationUseCase.invoke(body = latLng).collectResource(
                onSuccess = { reverseGeocodeLocation(latLng) })
        }
    }

    private fun changeNowAreaAvailable(show: Boolean) =
        updateState { copy(showNoAreaScreen = show) }

    private fun updatePlacePredictions(predictions: List<Pair<String, String>>) =
        updateState { copy(placePredictions = predictions) }

    private fun setPlacesClient(client: PlacesClient?) = updateState { copy(placesClient = client) }
    private fun setSessionToken(token: AutocompleteSessionToken?) =
        updateState { copy(sessionToken = token) }

    private fun setCurrentUserLocation(location: LatLng?) =
        updateState { copy(currentLocation = location) }

    private fun showResults(showResults: Boolean) =
        updateState { copy(showSearchResults = showResults) }

    private fun clearQuery() = updateState { copy(searchQuery = "") }
    private fun searchQuery(query: String) = updateState { copy(searchQuery = query) }
    private fun requestLocationPermission() = updateState { copy(isPermissionGranted = true) }
    private fun mapLoaded() = updateState { copy(isMapReady = true) }

    private suspend fun isValidLocation(latLng: LatLng): Boolean {
        val result = validateLocationUseCase.invoke(latLng)
        return result is Resource.Success && result.model == true
    }
}