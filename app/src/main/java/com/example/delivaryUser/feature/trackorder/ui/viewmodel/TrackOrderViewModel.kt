package com.example.delivaryUser.feature.trackorder.ui.viewmodel

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.loading.ILoadingEvent
import com.example.delivaryUser.common.ui.navigation.IMainGraph
import com.example.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.example.delivaryUser.feature.orders.base.domain.models.domain.Order
import com.example.delivaryUser.feature.orders.orderdetails.domain.ineractors.GetOrderDetailsUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class TrackOrderViewModel(
    savedStateHandle: SavedStateHandle,
    private val orderDetails: GetOrderDetailsUseCase,
    private val context: Context
) : BaseViewModel<TrackOrderContract.State, TrackOrderContract.Action>(TrackOrderContract.State()) {

    private val _effect = Channel<String>()
    val effect = _effect.receiveAsFlow()

    private val route = savedStateHandle.toRoute<IMainGraph.TrackOrder>()

    init {
        loadOrderDetails()
    }

    override fun onActionTrigger(action: TrackOrderContract.Action) {
        when (action) {
            is TrackOrderContract.Action.OnBackClicked -> onBackClicked()
            is TrackOrderContract.Action.OnCallDriverClicked -> onCallDriverClicked()
            is TrackOrderContract.Action.OnChatWithDriverClicked -> onChatWithDriverClicked()
        }
    }

    private fun loadOrderDetails() =
        viewModelScope.launch {
            orderDetails.invoke(body = route.id)
                .collectResource(onSuccess = ::loadOrderDetailsSuccess, onLoading = ::onLoading)
        }

    private fun loadOrderDetailsSuccess(order: Order) =
        updateState {
            copy(
                order = state.value.order.copy(
                    status = order.orderStatus,
                    id = order.id.toOrderId(),
                    price = order.deliveryPrice,
                    totalPrice = order.totalOrderPrice,
                    estimatedPrice = order.orderPrice,
                ),
                delivery = state.value.delivery.copy(
                    name = order.deliveryName,
                    number = order.deliveryPhone,
                    price = order.deliveryFees,
                    time = order.deliveryTime.toDeliverYTime(),
                    chatId = order.chatId,
                    latitude = order.deliveryLatitude,
                    longitude = order.deliveryLongitude
                ),
                client = state.value.client.copy(
                    name = order.clientName,
                    address = order.clientAddress,
                    startLatitude = order.clientLatitude,
                    startLongitude = order.clientLongitude,
                    endLatitude = order.endClientLatitude,
                    endLongitude = order.endClientLongitude,
                    image = order.userImage,
                ),
            )
        }

    private fun onBackClicked() = viewModelScope.launch {
        fireNavigateUp()
    }


    private fun onCallDriverClicked() = viewModelScope.launch {
        val phoneNumber = state.value.delivery.number

        if (phoneNumber.isNotEmpty()) {
            _effect.send(phoneNumber)
        }
    }

    private fun onChatWithDriverClicked() = viewModelScope.launch {
        //todo nav to chat screen
    }

    private fun onLoading(isLoading: Boolean) {
        fireLoading(ILoadingEvent.CircularProgressIndicator(isLoading))
    }

    private fun Int.toOrderId(): String = "#".plus(this)

    private fun String.toDeliverYTime(): String =
        this.plus(context.getString(R.string.minutes))


}