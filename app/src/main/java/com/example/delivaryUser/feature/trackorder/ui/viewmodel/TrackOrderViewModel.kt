package com.example.delivaryUser.feature.trackorder.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.delivaryUser.common.ui.loading.ILoadingEvent
import com.example.delivaryUser.common.ui.navigation.IMainGraph
import com.example.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.example.delivaryUser.feature.orders.base.domain.models.domain.Order
import com.example.delivaryUser.feature.orders.orderdetails.domain.ineractors.GetOrderDetailsUseCase
import com.example.delivaryUser.feature.orders.orderslist.domain.interactors.GetOrdersUseCase
import com.example.delivaryUser.feature.orders.orderslist.ui.viewmodel.toUiState
import kotlinx.coroutines.launch

class TrackOrderViewModel(
    savedStateHandle: SavedStateHandle,
    private val getOrdersUseCase: GetOrderDetailsUseCase
) : BaseViewModel<TrackOrderContract.State, TrackOrderContract.Action>(
    TrackOrderContract.State()
) {

    private val route = savedStateHandle.toRoute<IMainGraph.TrackOrder>()

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
            getOrdersUseCase.invoke(body = route.id)
                .collectResource(
                    onSuccess = { getOrderDetailsSuccess(order = it) },
                    onLoading = { onLoading(it) }
                )
        }
    }

    private fun onLoading(isLoading: Boolean) {
        fireLoading(ILoadingEvent.CircularProgressIndicator(isLoading))

    }

    private fun getOrderDetailsSuccess(order: Order) {
        updateState {
            copy(
                orderState = order.orderStatus,
                orderNumber = order.id.toString(),
                providerName = order.providerName,
                clientName = order.clientName,
                deliveryName = order.deliveryName,
                deliveryNumber = order.deliveryPhone,
                deliveryPrice = order.deliveryFees,
                orderPrice = order.deliveryPrice,
                totalPrice = order.totalOrderPrice,
                estimatedPrice = order.orderPrice,
                deliveryTime = order.deliveryTime,
                chatId = order.chatId,
                clientLatitude = order.clientLatitude,
                clientLongitude = order.clientLongitude,
                clientAddress = order.clientAddress,
            )
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