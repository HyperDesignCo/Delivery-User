package com.example.delivaryUser.feature.trackorder.ui.viewmodel

import com.google.android.gms.maps.model.LatLng

sealed interface TrackOrderContract {
    sealed interface Action {
        data object BackClicked : Action
        data object MapClicked : Action
        data object CallDriverClicked : Action
        data object ChatWithDriverClicked : Action
    }

    data class State(
        val deliveryTo: String = "",
        val userName: String = "",
        val userAddress: String = "",
        val userImage: String = "",
        val currentStep: Int = 0, // 0-3
        val driverName: String = "Delivery",
        val driverImage: String = "",
        val deliveryFee: String = "289.95",
        val totalPrice: String = "8558",
        val orderStatus: String = "",
        val refusedReason: String = "",
        val isRefused: Boolean = false,
    )
}