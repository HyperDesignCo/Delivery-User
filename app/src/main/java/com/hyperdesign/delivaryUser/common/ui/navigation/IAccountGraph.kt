package com.hyperdesign.delivaryUser.common.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hyperdesign.delivaryUser.feature.authentication.changepassword.ui.view.ChangePasswordScreen
import com.hyperdesign.delivaryUser.feature.contact.ui.view.ContactScreen
import com.hyperdesign.delivaryUser.feature.userdata.accountinfo.ui.view.AccountInfoScreen
import com.hyperdesign.delivaryUser.feature.userdata.editaccount.ui.view.EditAccountScreen
import com.hyperdesign.delivaryUser.feature.userdata.selectlanguage.ui.view.SelectLanguageScreen
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

    @Serializable
    data object Language : IDestination

    @Serializable
    data object GetHelp : IDestination
}
fun NavGraphBuilder.buildNavAccountGraph() {
    navigation<IAccountGraph.AccountGraph>(startDestination = IAccountGraph.AccountInfo) {
        composable<IAccountGraph.AccountInfo> { AccountInfoScreen() }
        composable<IAccountGraph.EditAccount> { EditAccountScreen() }
        composable<IAccountGraph.ChangePassword> { ChangePasswordScreen() }
        composable<IAccountGraph.Language> { SelectLanguageScreen() }
        composable<IAccountGraph.GetHelp> { ContactScreen() }
    }
}