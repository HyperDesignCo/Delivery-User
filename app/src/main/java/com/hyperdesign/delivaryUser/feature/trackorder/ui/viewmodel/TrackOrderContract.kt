package com.hyperdesign.delivaryUser.feature.trackorder.ui.viewmodel

import com.hyperdesign.delivaryUser.feature.orders.base.domain.models.domain.OrderStatus

sealed interface TrackOrderContract {
    sealed interface Action {
        data object OnBackClicked : Action
        data object OnCallDriverClicked : Action
        data object OnChatWithDriverClicked : Action
    }

    data class State(
        val order: OrderState = OrderState(),
        val delivery: DeliveryState = DeliveryState(),
        val client: Client = Client(),
        val isDataReady: Boolean = false
    )

    data class OrderState(
        val status: OrderStatus = OrderStatus.PENDING,
        val id: String = "",
        val price: String = "",
        val type: String = "",
        val totalPrice: String = "0.0",
        val estimatedPrice: String = "",
    )

    data class DeliveryState(
        val id: String = "",
        val name: String = "",
        val number: String = "",
        val price: String = "",
        val image: String = "",
        val time: String = "",
        val chatId: String = "",
        val latitude: String = "",
        val longitude: String = ""
    )

    data class Client(
        val name: String = "",
        val image: String = "",
        val address: String = "",
        val startLatitude: String = "",
        val startLongitude: String = "",
        val endLatitude: String = "",
        val endLongitude: String = "",
    )

}