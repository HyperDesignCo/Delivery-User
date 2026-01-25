package com.example.delivaryUser.feature.orders.orderdetails.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.delivaryUser.common.ui.navigation.IMainGraph
import com.example.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.example.delivaryUser.feature.orders.base.domain.models.domain.Order
import com.example.delivaryUser.feature.orders.orderdetails.domain.ineractors.GetOrderDetailsUseCase
import kotlinx.coroutines.launch

class OrderDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val useCase: GetOrderDetailsUseCase) :
    BaseViewModel<OrderDetailsContract.State, OrderDetailsContract.Action>(OrderDetailsContract.State()) {
private val route = savedStateHandle.toRoute<IMainGraph.OrderDetails>()
    init {
        getOrderDetails()
    }

    override fun onActionTrigger(action: OrderDetailsContract.Action) {
        when (action) {
            OrderDetailsContract.Action.OnBackClicked -> navigateBack()
        }
    }

    private fun getOrderDetails() {
        viewModelScope.launch {
            useCase.invoke(body = route.id)
                .collectResource(onSuccess = { getOrderDetailsSuccess(order = it) })
        }
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
                estimatedPrice= order.orderPrice
            )
        }
    }

    private fun navigateBack() {
        fireNavigate(IMainGraph.Home) {
            popUpTo(IMainGraph.OrderDetails) {
                inclusive = true
                saveState = false
            }
        }
    }
}