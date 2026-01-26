package com.example.delivaryUser.feature.orders.orderdetails.ui.viewmodel

import com.example.delivaryUser.feature.orders.base.domain.models.domain.OrderStatus

sealed interface OrderDetailsContract {
    sealed interface Action{
        data object OnBackClicked : Action
    }
    data class State(
        val orderState : OrderStatus = OrderStatus.PENDING,
        val orderNumber :String="",
        val providerName: String="",
        val clientName: String="",
        val deliveryName: String="",
        val deliveryNumber: String="",
        val deliveryPrice: String="",
        val orderPrice: String= "",
        val totalPrice : Double  = 0.0 ,
        val estimatedPrice : String = "",
    )
}