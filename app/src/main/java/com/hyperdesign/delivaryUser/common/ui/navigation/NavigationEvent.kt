package com.hyperdesign.delivaryUser.common.ui.navigation

import androidx.navigation.NavOptionsBuilder

sealed class NavigationEvent {
    data class Navigate(
        val destination: IDestination, val builder: NavOptionsBuilder.() -> Unit = {}
    ) : NavigationEvent()

    data object NavigateUp : NavigationEvent()
}