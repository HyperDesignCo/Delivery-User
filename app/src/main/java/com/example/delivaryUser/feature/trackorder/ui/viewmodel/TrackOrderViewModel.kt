package com.example.delivaryUser.feature.trackorder.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.delivaryUser.common.ui.navigation.IMainGraph
import com.example.delivaryUser.common.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class TrackOrderViewModel : BaseViewModel<TrackOrderContract.State, TrackOrderContract.Action>(
    TrackOrderContract.State()
) {
    init {
        loadOrderData()
    }

    override fun onActionTrigger(action: TrackOrderContract.Action) {
        when (action) {
            is TrackOrderContract.Action.BackClicked -> navigateBack()
            is TrackOrderContract.Action.MapClicked -> openMap()
            is TrackOrderContract.Action.CallDriverClicked -> callDriver()
            is TrackOrderContract.Action.ChatWithDriverClicked -> chatWithDriver()
        }
    }

    private fun loadOrderData() {
        viewModelScope.launch {
            // Load order details from repository
            updateState {
                copy(
                    deliveryTo = "Delivery to",
                    userName = "Ahmed Hassan",
                    userAddress = "123 Main Street, Cairo",
                    currentStep = 1
                )
            }
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            fireNavigateUp()
        }
    }

    private fun openMap() {
        viewModelScope.launch {
            // Navigate to map with location details
        }
    }

    private fun callDriver() {
        viewModelScope.launch {
            // Trigger phone call
        }
    }

    private fun chatWithDriver() {
        viewModelScope.launch {
            // Navigate to chat screen
        }
    }
}