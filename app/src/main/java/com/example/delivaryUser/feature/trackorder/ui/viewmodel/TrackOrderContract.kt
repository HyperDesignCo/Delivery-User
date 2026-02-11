package com.example.delivaryUser.feature.trackorder.ui.viewmodel

import com.example.delivaryUser.feature.orders.base.domain.models.domain.OrderStatus
import com.google.android.gms.maps.model.LatLng

sealed interface TrackOrderContract {
    sealed interface Action {
        data object BackClicked : Action
        data object MapClicked : Action
        data object CallDriverClicked : Action
        data object ChatWithDriverClicked : Action
    }

    data class State(
        val orderState : OrderStatus = OrderStatus.PENDING,
        val orderNumber :String="",
        val providerName: String="",
        val clientName: String="",
        val deliveryName: String="",
        val deliveryNumber: String="",
        val deliveryPrice: String="",
        val clientAddress:String="",
        val clientLatitude:String="",
        val clientLongitude:String="",
        val orderPrice: String= "",
        val chatId:String="",
        val deliveryTime:String="",
        val totalPrice : String  = "0.0" ,
        val estimatedPrice : String = "",
    )
}