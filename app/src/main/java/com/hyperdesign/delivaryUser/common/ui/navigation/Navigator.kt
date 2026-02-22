package com.hyperdesign.delivaryUser.common.ui.navigation

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class Navigator(override val startGraph: IGraph) : INavigator {
    private val _navigateEvent = Channel<NavigationEvent>()
    override val navigationEvent = _navigateEvent.receiveAsFlow()

    override suspend fun navigate(destination: IDestination, builder: NavOptionsBuilder.() -> Unit) =
        _navigateEvent.send(NavigationEvent.Navigate(destination = destination, builder = builder))

    override suspend fun navigateUp() = _navigateEvent.send(NavigationEvent.NavigateUp)
}