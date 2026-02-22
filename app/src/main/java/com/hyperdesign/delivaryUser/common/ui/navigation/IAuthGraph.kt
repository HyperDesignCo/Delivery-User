package com.hyperdesign.delivaryUser.common.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hyperdesign.delivaryUser.feature.authentication.forgetpassword.ui.view.ForgetPasswordScreen
import com.hyperdesign.delivaryUser.feature.authentication.login.ui.view.LoginScreen
import com.hyperdesign.delivaryUser.feature.authentication.register.ui.view.RegisterScreen
import com.hyperdesign.delivaryUser.feature.authentication.splash.ui.view.SplashScreen
import com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.ui.view.VerifyOtpScreen
import kotlinx.serialization.Serializable

sealed interface IAuthGraph {
    @Serializable
    data object AuthGraph : IGraph

    @Serializable
    data object Login : IDestination

    @Serializable
    data object Register : IDestination

    @Serializable
    data class VerifyOtp(val phone: String) : IDestination

    @Serializable
    data object ForgetPassword : IDestination

    @Serializable
    data object Splash : IDestination
}

fun NavGraphBuilder.buildNavAuthGraph() {
    navigation<IAuthGraph.AuthGraph>(startDestination = IAuthGraph.Splash) {
        composable<IAuthGraph.Login> { LoginScreen() }
        composable<IAuthGraph.Register> { RegisterScreen() }
        composable<IAuthGraph.VerifyOtp> { VerifyOtpScreen() }
        composable<IAuthGraph.ForgetPassword> { ForgetPasswordScreen() }
        composable<IAuthGraph.Splash> { SplashScreen() }
    }
}