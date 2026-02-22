package com.hyperdesign.delivaryUser.feature.contact.ui.viewmodel

import androidx.annotation.DrawableRes

sealed interface ContactScreenContract {
    sealed interface Action : ContactScreenContract {
        data object OnBackClicked : Action
        data class OnCallClicked(val link: String) : Action
    }

    data class State(
        val email: String = "",
        val phone: String = "",
        val socialMediaItems: List<SocialMediaState> = emptyList(),
    )

    data class SocialMediaState(
        @param:DrawableRes val icon: Int = 0,
        val link: String = "",
    )
}