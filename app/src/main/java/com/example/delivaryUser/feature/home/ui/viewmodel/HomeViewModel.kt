package com.example.delivaryUser.feature.home.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.delivaryUser.common.ui.loading.ILoadingEvent
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

            is HomeContract.Action.FastOrderClicked -> {
                // TODO NAVIGATE TO FAST ORDER SCREEN
            }

            is HomeContract.Action.NavigateBacKClicked -> navigateBack()
            is HomeContract.Action.OnAddLocationClicked -> {

            }

            is HomeContract.Action.OnLocationClicked -> {

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

    private fun navigateBack() {
        viewModelScope.launch {
            fireNavigateUp()
        }
    }
}