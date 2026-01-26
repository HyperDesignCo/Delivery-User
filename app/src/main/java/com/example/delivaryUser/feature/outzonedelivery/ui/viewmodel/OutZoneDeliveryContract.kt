package com.example.delivaryUser.feature.outzonedelivery.ui.viewmodel

import com.example.delivaryUser.feature.outzonedelivery.domain.model.AddAreaResponse

sealed interface OutZoneDeliveryContract {

    sealed interface Action {
        data object OnVoteClickAction : Action
        data object OnChangeLocation : Action

    }

    data class State(
        val addAreaRequestResponse: AddAreaResponse? = null
    )


}