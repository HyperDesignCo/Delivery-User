package com.example.delivaryUser.feature.home.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.delivaryUser.common.ui.extension.UIText
import com.example.delivaryUser.common.ui.loading.ILoadingEvent
import com.example.delivaryUser.common.ui.message.IMessageEvent
import com.example.delivaryUser.common.ui.navigation.IMainGraph
import com.example.delivaryUser.common.ui.navigation.IOrderGraph
import com.example.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.example.delivaryUser.feature.address.mapview.domain.usecase.GetLocationResponseUseCase
import com.example.delivaryUser.feature.address.mapview.domain.usecase.GetSavedLocationUseCase
import com.example.delivaryUser.feature.address.mapview.domain.usecase.SaveLocationUseCase
import com.example.delivaryUser.feature.home.domain.interactors.GetAdsUseCase
import com.example.delivaryUser.feature.home.domain.models.Ads
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
    private val getLocationResponseUseCase: GetLocationResponseUseCase,
    private val getSavedLocationUseCase: GetSavedLocationUseCase,
) : BaseViewModel<HomeContract.State, HomeContract.Action>(HomeContract.State()) {
    init {
        loadSavedLocationFirst()
    }

    override fun onActionTrigger(action: HomeContract.Action) {
        when (action) {
            is HomeContract.Action.ChatWithAiClicked -> {}
            is HomeContract.Action.FastOrderClicked -> onFastOrderClicked()
            is HomeContract.Action.NavigateBacKClicked -> navigateBack()
            is HomeContract.Action.OnAddLocationClicked -> {
                fireNavigate(destination = IOrderGraph.Map())
            }

            is HomeContract.Action.OnLocationClicked -> {
                fireNavigate(destination = IOrderGraph.Map())
            }

            is HomeContract.Action.OnNewOrderClicked -> {
                fireNavigate(destination = IOrderGraph.FastOrder)
            }

            HomeContract.Action.OnPointToPointClicked -> {
                fireNavigate(IOrderGraph.PointToPoint)
            }

            is HomeContract.Action.OnChangeLocation -> {
                changeLocation(action.latLng)
            }
        }
    }

    private fun loadSavedLocationFirst() {
        viewModelScope.launch(Dispatchers.IO) {
            getSavedLocationUseCase.invoke(Unit).collectResource(
                onSuccess = { savedLatLng ->
                    updateState {
                        copy(savedLatLng = savedLatLng)
                    }
                    loadLocation()
                    getAds()
                })
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
                })
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
        val targetLatLng = state.value.latLng ?: return

        val request = CheckLocationRequest(
            latitude = targetLatLng.latitude.toString(),
            longitude = targetLatLng.longitude.toString()
        )

        viewModelScope.launch(Dispatchers.IO) {
            checkLocationUseCase.invoke(request).collectResource(
                onSuccess = { checkLocationResponse ->
                    handleCheckLocationResponse(
                        checkLocation = checkLocationResponse, targetLocation = targetLatLng
                    )
                })
        }
    }

    private fun handleCheckLocationResponse(
        checkLocation: CheckLocation, targetLocation: LatLng,
    ) {
        updateState {
            copy(checkLocationResponse = checkLocation)
        }

        val currentRegion = checkLocation.data?.currentRegion
        val currentArea = checkLocation.data?.currentArea
        val savedLatLng = state.value.savedLatLng

        if (savedLatLng != null && isSameLocation(savedLatLng, targetLocation)) {
            return
        }

        if (currentRegion.isNullOrEmpty() || currentArea.isNullOrEmpty()) {
            saveLocation(targetLocation)

            fireNavigate(
                IMainGraph.DeliveryOutZone(
                    latitude = targetLocation.latitude,
                    longitude = targetLocation.longitude,
                )
            )
        } else {
            displayAndSaveLocation(checkLocation.data)
            saveLocation(targetLocation)
        }
    }

    private fun isSameLocation(
        savedLatLng: LatLng, targetLocation: LatLng,
    ): Boolean {
        val tolerance = 0.0002

        val latDiff = Math.abs(savedLatLng.latitude - targetLocation.latitude)
        val lngDiff = Math.abs(savedLatLng.longitude - targetLocation.longitude)

        val isSame = latDiff < tolerance && lngDiff < tolerance

        return isSame
    }

    private fun displayAndSaveLocation(data: Location) {
        val regionName = data.currentRegionName.orEmpty()
        val areaName = data.currentAreaName.orEmpty()
        val displayLocation = "$regionName,$areaName"

        updateState {
            copy(location = displayLocation)
        }
    }

    private fun saveLocation(latLng: LatLng) {
        viewModelScope.launch {
            saveLocationUseCase.invoke(body = latLng).collectResource(
                onSuccess = {
                    updateState {
                        copy(savedLatLng = latLng)
                    }
                })
        }
    }

    private fun changeLocation(latLng: LatLng) {

        updateState {
            copy(latLng = latLng)
        }
        checkLocationFromApi()
    }

    private fun getAds() {
        viewModelScope.launch {
            useCase.invoke(body = Unit).collectResource(onSuccess = {
                onGetAdsUseCaseSuccess(it)
            }, onLoading = {
                fireLoading(ILoadingEvent.CircularProgressIndicator(isLoading = it))
            })
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