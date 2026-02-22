package com.hyperdesign.delivaryUser.feature.orders.orderslist.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.ui.extension.UIText
import com.hyperdesign.delivaryUser.common.ui.message.IMessageEvent
import com.hyperdesign.delivaryUser.common.ui.message.MessageType
import com.hyperdesign.delivaryUser.common.ui.navigation.IMainGraph
import com.hyperdesign.delivaryUser.common.ui.navigation.IMainGraph.OrderDetails
import com.hyperdesign.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.hyperdesign.delivaryUser.feature.orders.base.data.models.request.RateOrderRequest
import com.hyperdesign.delivaryUser.feature.orders.orderslist.domain.interactors.GetOrdersUseCase
import com.hyperdesign.delivaryUser.feature.orders.orderslist.domain.interactors.RateOrderUseCase
import kotlinx.coroutines.launch

class OrdersListViewModel(private val orders: GetOrdersUseCase, private val rate: RateOrderUseCase) :
    BaseViewModel<OrdersContract.State, OrdersContract.Action>(OrdersContract.State()) {
    override fun onActionTrigger(action: OrdersContract.Action) {
        when (action) {
            is OrdersContract.Action.OrderClicked -> {
                fireNavigate(destination = OrderDetails(id = action.id))
            }

            OrdersContract.Action.OnBackClicked -> {
                navigateBack()
            }

            is OrdersContract.Action.OnCloseDialogClicked -> onCloseDialogClicked()
            is OrdersContract.Action.OnOpenDialogClicked -> onOpenDialogClicked()
            is OrdersContract.Action.OnCommentChanged -> onCommentChanged(action.comment)
            is OrdersContract.Action.OnRatingClicked -> onRatingClicked(action.index)
            is OrdersContract.Action.OnSendRateClicked -> onSendRateClicked()
            is OrdersContract.Action.Init ->   getOrders()
        }
    }

    private fun getOrders() {
        viewModelScope.launch {
            orders.invoke(Unit).collectResource(onSuccess = { orders ->
                updateState { copy(orders = orders.map { it.toUiState() },) }
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

    private fun onOpenDialogClicked() {
        updateState { copy(rate = rate.copy(isDialogVisible = true)) }
    }

    private fun onCloseDialogClicked() {
        updateState { copy(rate = rate.copy(isDialogVisible = false)) }
    }

    private fun onCommentChanged(comment: String) {
        updateState {
            copy(rate = this.rate.copy(comment = comment))
        }
    }

    private fun onRatingClicked(index: Int) {
        updateState {
            copy(rate = rate.copy(rate = index + 1))
        }
    }

    private fun onSendRateClicked() {
        val request = RateOrderRequest(
            orderId = state.value.rate.orderId,
            rate = state.value.rate.rate,
            comment = state.value.rate.comment,
        )
        viewModelScope.launch {
            rate.invoke(body = request)
                .collectResource(onSuccess = {
                    updateState { copy(rate = rate.copy(isDialogVisible = false)) }
                    fireMessage(IMessageEvent.Snackbar(
                        UIText.StringResource(R.string.we_received_your_rate),
                        messageType = MessageType.SUCCESS
                    ))
                })
        }
    }
}