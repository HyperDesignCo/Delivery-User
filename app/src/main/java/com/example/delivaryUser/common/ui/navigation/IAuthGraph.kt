package com.example.delivaryUser.common.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.delivaryUser.feature.authentication.forgetpassword.ui.view.ForgetPasswordScreen
import com.example.delivaryUser.feature.authentication.login.ui.view.LoginScreen
import com.example.delivaryUser.feature.authentication.register.ui.view.RegisterScreen
import com.example.delivaryUser.feature.authentication.verifyOtp.ui.view.VerifyOtpScreen
import kotlinx.serialization.Serializable

sealed interface IAuthGraph {
    @Serializable
    data object AuthGraph : IGraph

    @Serializable
    data object Login : IDestination

    @Serializable
    data object Register : IDestination

    @Serializable
    data object VerifyOtp : IDestination

    @Serializable
    data object ForgetPassword : IDestination
}

fun NavGraphBuilder.buildNavAuthGraph() {
    navigation<IAuthGraph.AuthGraph>(startDestination = IAuthGraph.Login) {
        composable<IAuthGraph.Login> { LoginScreen() }
        composable<IAuthGraph.Register> { RegisterScreen() }
        composable<IAuthGraph.VerifyOtp> { VerifyOtpScreen() }
        composable<IAuthGraph.ForgetPassword> { ForgetPasswordScreen() }
    }
}