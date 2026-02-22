package com.example.delivaryUser.feature.deliveryoutzone.ui.viewmodel

sealed interface DeliveryOutZoneContract {
    sealed interface Action {
        data object OnVoteClicked : Action
        data object OnChangeLocationClicked : Action
    }
}