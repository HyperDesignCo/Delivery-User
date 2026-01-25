package com.example.delivaryUser.feature.orders.orderslist.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.delivaryUser.common.ui.navigation.IMainGraph
import com.example.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.example.delivaryUser.feature.orders.orderslist.domain.interactors.GetOrdersUseCase
import kotlinx.coroutines.launch

class OrdersListViewModel(private val useCase: GetOrdersUseCase) :
    BaseViewModel<OrdersContract.State, OrdersContract.Action>(OrdersContract.State()) {
    init {
        getOrders()
    }

    override fun onActionTrigger(action: OrdersContract.Action) {
        when (action) {
            is OrdersContract.Action.OrderClicked -> {
                fireNavigate(destination = IMainGraph.OrderDetails(id = action.id))
            }

            OrdersContract.Action.OnBackClicked -> {
                navigateBack()
            }
        }
    }

    private fun getOrders() {
        viewModelScope.launch {
            useCase.invoke(Unit).collectResource(onSuccess = { orders ->
                updateState { copy(orders = orders.map { it.toUiState() }) }
            })
        }
    }

    private fun navigateBack() {
        fireNavigate(IMainGraph.Home) {
            popUpTo(IMainGraph.Orders) {
                inclusive = true
                saveState = false
            }
        }
    }
}