package com.example.delivaryUser.common.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navigation
import com.example.delivaryUser.feature.authentication.login.ui.view.LoginScreen
import kotlinx.serialization.Serializable

sealed interface IAuthGraph {
    @Serializable
    data object AuthGraph : IGraph

    @Serializable
    data object Login : IDestination

}

fun NavGraphBuilder.buildNavAuthGraph() {
    navigation<IAuthGraph.AuthGraph>(startDestination = IAuthGraph.Login) {
        composable<IAuthGraph.Login> { LoginScreen() }
    }
}