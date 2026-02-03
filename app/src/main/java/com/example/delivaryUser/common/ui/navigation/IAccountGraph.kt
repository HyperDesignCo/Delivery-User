package com.example.delivaryUser.common.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.delivaryUser.feature.authentication.changepassword.ui.view.ChangePasswordScreen
import com.example.delivaryUser.feature.userdata.accountinfo.ui.view.AccountInfoScreen
import com.example.delivaryUser.feature.userdata.editaccount.ui.view.EditAccountScreen
import kotlinx.serialization.Serializable


interface IAccountGraph {
    @Serializable
    data object AccountGraph : IGraph

    @Serializable
    data object AccountInfo : IDestination
    @Serializable
    data object EditAccount : IDestination
    @Serializable
    data object ChangePassword : IDestination
}
fun NavGraphBuilder.buildNavAccountGraph() {
    navigation<IAccountGraph.AccountGraph>(startDestination = IAccountGraph.AccountInfo) {
        composable<IAccountGraph.AccountInfo> { AccountInfoScreen() }
        composable<IAccountGraph.EditAccount> { EditAccountScreen() }
        composable<IAccountGraph.ChangePassword> { ChangePasswordScreen() }
    }
}