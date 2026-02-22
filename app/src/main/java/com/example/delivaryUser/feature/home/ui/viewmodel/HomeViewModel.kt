package com.example.delivaryUser.feature.home.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.delivaryUser.common.ui.loading.ILoadingEvent
import com.example.delivaryUser.common.ui.navigation.IMainGraph
import com.example.delivaryUser.common.ui.navigation.IOrderGraph
import com.example.delivaryUser.common.ui.navigation.IOrderGraph.Map
import com.example.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.example.delivaryUser.feature.home.domain.interactors.GetAdsUseCase
import com.example.delivaryUser.feature.home.domain.models.HomeData
import com.example.delivaryUser.service.location.domain.LocationResult
import com.example.delivaryUser.service.location.domain.interactors.ResolveLocationUseCase
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: GetAdsUseCase,
    private val resolveLocationUseCase: ResolveLocationUseCase,
) : BaseViewModel<HomeContract.State, HomeContract.Action>(HomeContract.State()) {
    init {
        loadLocation()
        getHomeData()
    }

    override fun onActionTrigger(action: HomeContract.Action) {
        when (action) {
            is HomeContract.Action.ChatWithAiClicked -> navigateToChatWithAi()
            is HomeContract.Action.FastOrderClicked -> onFastOrderClicked()
            is HomeContract.Action.NavigateBacKClicked -> navigateBack()
            is HomeContract.Action.OnAddLocationClicked -> fireNavigate(Map())
            is HomeContract.Action.OnLocationClicked -> fireNavigate(Map())
            is HomeContract.Action.OnNewOrderClicked -> fireNavigate(IOrderGraph.FastOrder)
            is HomeContract.Action.OnPointToPointClicked -> fireNavigate(IOrderGraph.PointToPoint)
            is HomeContract.Action.OnChangeLocation -> resolveLocation(action.latLng)
            is HomeContract.Action.OnTrackOrderClicked -> fireNavigate(IMainGraph.TrackOrder(action.id))
        }
    }

    private fun loadLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            val latLng = state.value.latLng ?: return@launch
            resolveLocation(latLng)
        }
    }

    private fun resolveLocation(latLng: LatLng) {
        viewModelScope.launch(Dispatchers.IO) {
            resolveLocationUseCase.invoke(latLng).collectResource(
                onSuccess = { result ->
                    when (result) {
                        is LocationResult.LocationFound -> {
                            updateState {
                                copy(
                                    location = result.displayLocation,
                                    savedLatLng = result.savedLatLng,
                                    latLng = result.savedLatLng
                                )
                            }
                        }

                        is LocationResult.OutOfZone -> {
                            fireNavigate(
                                IMainGraph.DeliveryOutZone(
                                    latitude = result.latitude,
                                    longitude = result.longitude,
                                )
                            )
                        }

                        LocationResult.SameLocation -> Unit
                    }
                }
            )
        }
    }

    private fun getHomeData() {
        viewModelScope.launch {
            useCase.invoke(Unit).collectResource(
                onSuccess = { onGetHomeDataSuccess(it) },
                onLoading = { fireLoading(ILoadingEvent.CircularProgressIndicator(isLoading = it)) }
            )
        }
    }

    private fun onGetHomeDataSuccess(homeData: HomeData) {
        updateState {
            copy(
                ads = homeData.ads.map { it.toUiState() },
                trackOrders = homeData.orders.map { it.toUiState() }
            )
        }
    }

    private fun onFastOrderClicked() {
        updateState { copy(isButtonsVisible = isButtonsVisible.not()) }
    }

    private fun navigateToChatWithAi() {
        viewModelScope.launch { fireNavigate(IMainGraph.Chat) }
    }

    private fun navigateBack() {
        viewModelScope.launch { fireNavigateUp() }
    }
}