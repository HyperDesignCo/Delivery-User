package com.example.delivaryUser.feature.trackorder.ui.viewmodel

import com.example.delivaryUser.feature.orders.base.domain.models.domain.OrderStatus
sealed interface TrackOrderContract {
    sealed interface Action {
        data object OnBackClicked : Action
        data object OnMapClicked : Action
        data object OnCallDriverClicked : Action
        data object OnChatWithDriverClicked : Action
    }

    data class State(
        val order: OrderState = OrderState(),
        val delivery: DeliveryState = DeliveryState(),
        val client: Client = Client(),
    )

    data class OrderState(
        val status: OrderStatus = OrderStatus.PENDING,
        val id: String = "",
        val price: String = "",
        val totalPrice: String = "0.0",
        val estimatedPrice: String = "",
    )

    data class DeliveryState(
        val name: String = "",
        val number: String = "",
        val price: String = "",
        val time: String = "",
        val chatId: String = "",
    )

    data class Client(
        val name: String = "",
        val image : String = "",
        val address: String = "",
        val latitude: String = "",
        val longitude: String = "",
    )
}