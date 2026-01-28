package com.example.delivaryUser.feature.home.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.delivaryUser.common.ui.loading.ILoadingEvent
import com.example.delivaryUser.common.ui.navigation.IAddressGraph
import com.example.delivaryUser.common.ui.navigation.IOrderGraph
import com.example.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.example.delivaryUser.feature.home.domain.interactors.GetAdsUseCase
import com.example.delivaryUser.feature.home.domain.models.Ads
import kotlinx.coroutines.launch

class HomeViewModel(private val useCase: GetAdsUseCase) :
    BaseViewModel<HomeContract.State, HomeContract.Action>(HomeContract.State()) {
    init {
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
            fireNavigate(IOrderGraph.PointToPoint)
            }
        }
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

    private fun onFastOrderClicked(){
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