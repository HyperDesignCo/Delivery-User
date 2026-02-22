package com.hyperdesign.delivaryUser.feature.home.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.hyperdesign.delivaryUser.common.ui.loading.ILoadingEvent
import com.hyperdesign.delivaryUser.common.ui.navigation.IMainGraph
import com.hyperdesign.delivaryUser.common.ui.navigation.IOrderGraph
import com.hyperdesign.delivaryUser.common.ui.navigation.IOrderGraph.Map
import com.hyperdesign.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors.GetLocationResponseUseCase
import com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors.GetSavedLocationUseCase
import com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors.SaveLocationUseCase
import com.hyperdesign.delivaryUser.feature.home.domain.interactors.GetAdsUseCase
import com.hyperdesign.delivaryUser.feature.home.domain.models.HomeData
import com.hyperdesign.delivaryUser.service.location.data.model.request.CheckLocationRequest
import com.hyperdesign.delivaryUser.service.location.domain.interactors.CheckLocationUseCase
import com.hyperdesign.delivaryUser.service.location.domain.model.CheckLocation
import com.hyperdesign.delivaryUser.service.location.domain.model.Location
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
        getHomeData()
    }

    override fun onActionTrigger(action: HomeContract.Action) {
        when (action) {
            is HomeContract.Action.ChatWithAiClicked -> navigateToChatWithAi()
            is HomeContract.Action.FastOrderClicked -> onFastOrderClicked()
            is HomeContract.Action.NavigateBacKClicked -> navigateBack()
            is HomeContract.Action.OnAddLocationClicked -> {
                fireNavigate(destination = Map())
            }

            is HomeContract.Action.OnLocationClicked -> {
                fireNavigate(destination = Map())
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

            is HomeContract.Action.OnTrackOrderClicked -> {
                fireNavigate(IMainGraph.TrackOrder(action.id))
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
        if (areaName.isNotEmpty() || regionName.isNotEmpty()) {
            updateState {
                copy(location = displayLocation)
            }
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
        val displayLocation = state.value.location
        val savedLatLng = state.value.savedLatLng

        if (savedLatLng != null && isSameLocation(savedLatLng, targetLocation)) {
            return
        }

        if (displayLocation.isEmpty() && currentRegion.isNullOrEmpty() || currentArea.isNullOrEmpty()) {
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

        if (areaName.isNotEmpty() || regionName.isNotEmpty()) {
            updateState {
                copy(location = displayLocation)
            }
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

        if (state.value.savedLatLng == null) {
            checkLocationFromApi()
        }
    }

    private fun getHomeData() {
        viewModelScope.launch {
            useCase.invoke(body = Unit).collectResource(onSuccess = {
                onGetHomeDataSuccess(it)
            }, onLoading = {
                fireLoading(ILoadingEvent.CircularProgressIndicator(isLoading = it))
            })
        }
    }

    private fun onGetHomeDataSuccess(homeData: HomeData) {
        updateState {
            copy(
                ads = homeData.ads.map { it.toUiState() },
                trackOrders = homeData.orders.map { it.toUiState() })
        }
    }

    private fun onFastOrderClicked() {
        viewModelScope.launch {
            updateState { copy(isButtonsVisible = isButtonsVisible.not()) }
        }
    }

    private fun navigateToChatWithAi() {
        viewModelScope.launch {
            fireNavigate(IMainGraph.Chat)
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            fireNavigateUp()
        }
    }
}