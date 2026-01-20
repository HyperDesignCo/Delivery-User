package com.example.delivaryUser.feature.home.ui.viewmodel

import com.example.delivaryUser.feature.home.domain.models.Ad
import com.example.delivaryUser.feature.home.ui.viewmodel.HomeContract.AdUiState

sealed interface HomeContract {
    sealed interface Action {
        data object FastOrderClicked : Action
        data object ChatWithAiClicked : Action
        data object NavigateBacKClicked : Action
        data class OnLocationClicked(val location: String) : Action
        data object OnAddLocationClicked : Action
    }

    data class State(
        val location: String = "",
        val ads: List<AdUiState> = emptyList(),
    )

    data class AdUiState(
        val id: String = "",
        val image: String = "",
    )
}

fun Ad.toUiState() = AdUiState(
    id = this.id.toString(),
    image = this.image,
)