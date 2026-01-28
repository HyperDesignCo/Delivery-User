package com.example.delivaryUser.feature.home.ui.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.delivaryUser.common.ui.extension.UIText
import com.example.delivaryUser.common.ui.loading.ILoadingEvent
import com.example.delivaryUser.common.ui.message.IMessageEvent
import com.example.delivaryUser.common.ui.navigation.IAddressGraph
import com.example.delivaryUser.common.ui.navigation.IOrderGraph
import com.example.delivaryUser.common.ui.navigation.IMainGraph
import com.example.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.example.delivaryUser.feature.address.mapview.domain.usecase.GetLocationResponseUseCase
import com.example.delivaryUser.feature.address.mapview.domain.usecase.SaveLocationResponseUseCase
import com.example.delivaryUser.feature.address.mapview.domain.usecase.SaveLocationUseCase
import com.example.delivaryUser.feature.home.domain.interactors.GetAdsUseCase
import com.example.delivaryUser.feature.home.domain.models.Ads
import com.example.delivaryUser.feature.outzonedelivery.domain.OpenDeliveryZone
import com.example.delivaryUser.service.location.data.model.request.CheckLocationRequest
import com.example.delivaryUser.service.location.domain.interactors.CheckLocationUseCase
import com.example.delivaryUser.service.location.domain.model.CheckLocation
import com.example.delivaryUser.service.location.domain.model.Location
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: GetAdsUseCase,
    private val checkLocationUseCase: CheckLocationUseCase,
    private val saveLocationUseCase: SaveLocationUseCase,
    private val savedLocationResponseUseCase: SaveLocationResponseUseCase,
    private val getLocationResponseUseCase: GetLocationResponseUseCase
) :
    BaseViewModel<HomeContract.State, HomeContract.Action>(HomeContract.State()) {
    init {
        loadLocation()
        getAds()
    }

    override fun onActionTrigger(action: HomeContract.Action) {
        when (action) {
            is HomeContract.Action.ChatWithAiClicked -> {
                // TODO NAVIGATE TO CHAT WITH AI SCREEN
            }

            is HomeContract.Action.FastOrderClicked -> onFastOrderClicked()
            is HomeContract.Action.NavigateBacKClicked -> navigateBack()
            is HomeContract.Action.OnAddLocationClicked -> {
                fireNavigate(destination = IAddressGraph.Map)
            }

            is HomeContract.Action.OnLocationClicked -> {
                fireNavigate(destination = IAddressGraph.Map)
            }

            HomeContract.Action.OnNewOrderClicked -> {
                // TODO NAVIGATE TO NEW ORDER
            }

            HomeContract.Action.OnPointToPointClicked -> {
                // TODO NAVIGATE TO POINT TO POINT
            fireNavigate(IOrderGraph.PointToPoint)
            }

            is HomeContract.Action.OnChangeLocation -> {
                changeLocation(action.latLng)
            }
        }
    }
    private fun loadLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            getLocationResponseUseCase.invoke(Unit).collectResource(
                onSuccess = { savedLocation ->
                    if (savedLocation != null) {
                        displaySavedLocation(savedLocation)
                    } else {
                        checkLocationFromApi()
                    }
                }
            )
        }
    }

    private fun displaySavedLocation(savedLocation: Location) {
        val regionName = savedLocation.currentRegionName.orEmpty()
        val areaName = savedLocation.currentAreaName.orEmpty()
        val displayLocation = "$regionName,$areaName"

        updateState {
            copy(location = displayLocation)
        }
    }
    private fun checkLocationFromApi() {
        val targetLatLng = state.value.latLng

        if (targetLatLng == null) {
//            fireMessage(
//                IMessageEvent.Toast(
//                    message = UIText.DynamicString("Location not available. Please enable location permissions.")
//                )
//            )
            return
        }

        val request = CheckLocationRequest(
            latitude = targetLatLng.latitude.toString(),
            longitude = targetLatLng.longitude.toString()
        )

        viewModelScope.launch(Dispatchers.IO) {
            checkLocationUseCase.invoke(request).collectResource(
                onSuccess = { checkLocationResponse ->
                    handleCheckLocationResponse(
                        checkLocation = checkLocationResponse,
                        targetLocation = targetLatLng
                    )
                },
            )
        }
    }

    private fun handleCheckLocationResponse(
        checkLocation: CheckLocation,
        targetLocation: LatLng
    ) {
        fireMessage(IMessageEvent.Toast(message = UIText.DynamicString(checkLocation.message)))
        updateState {
            copy(checkLocationResponse = checkLocation)
        }

        val currentRegion = checkLocation.data?.currentRegion
        val currentArea = checkLocation.data?.currentArea

        if (currentRegion.isNullOrEmpty() || currentArea.isNullOrEmpty()) {
            Log.d("teeeest","tttesset")
            fireNavigate(
                IMainGraph.OutSideZoneDelivery(
                    lat = targetLocation.latitude.toString(),
                    lng = targetLocation.longitude.toString(),
                    openDeliveryZone = OpenDeliveryZone.HOME_SCREEN
                )
            )
        } else {
            displayAndSaveLocation(checkLocation.data)
            saveLocation(latLng = targetLocation)
        }
    }


    private fun displayAndSaveLocation(data: Location) {
        val regionName = data.currentRegionName.orEmpty()
        val areaName = data.currentAreaName.orEmpty()
        val displayLocation = "$regionName,$areaName"

        updateState {
            copy(location = displayLocation)
        }

        saveLocationData(data)
    }

    private fun saveLocationData(data: Location) {
        viewModelScope.launch {
            savedLocationResponseUseCase.invoke(body = data)
        }
    }

    private fun saveLocation(latLng: LatLng) {
        viewModelScope.launch {
            saveLocationUseCase.invoke(body = latLng).collectResource()
        }
    }

    private fun changeLocation(latLng: LatLng) {
        updateState {
            copy(
                latLng = latLng,
                location = ""
            )
        }
        checkLocationFromApi()
    }
    private fun getAds() {
        viewModelScope.launch {
            useCase.invoke(body = Unit).collectResource(
                onSuccess = {
                    onGetAdsUseCaseSuccess(it)
                },
                onLoading = {
                    fireLoading(ILoadingEvent.CircularProgressIndicator(isLoading = it))
                }
            )
        }
    }

    private fun onGetAdsUseCaseSuccess(ads: Ads) {
        updateState { copy(ads = ads.ads.map { it.toUiState() }) }
    }

    private fun onFastOrderClicked() {
        viewModelScope.launch {
            updateState { copy(isButtonsVisible = isButtonsVisible.not()) }
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            fireNavigateUp()
        }
    }
}