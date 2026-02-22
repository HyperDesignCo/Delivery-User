package com.example.delivaryUser.common.ui.navigation

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.flow.Flow

interface INavigator {
    val startGraph: IGraph
    val navigationEvent: Flow<NavigationEvent>
    suspend fun navigate(destination: IDestination, builder: NavOptionsBuilder.() -> Unit = {})
    suspend fun navigateUp()
}