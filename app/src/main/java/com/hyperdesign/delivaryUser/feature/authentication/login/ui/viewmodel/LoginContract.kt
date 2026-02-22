package com.hyperdesign.delivaryUser.feature.authentication.login.ui.viewmodel

import com.hyperdesign.delivaryUser.common.ui.filedstate.TextFieldState

interface LoginContract {
    sealed interface Action {
        data class PhoneChanged(val phoneNumber: String) : Action
        data class PasswordChanged(val password: String) : Action
        data object LoginClicked : Action
        data object ForgotPasswordClicked : Action
        data object RegisterClicked : Action
        data object RememberMeClicked : Action
    }

    data class State(
        val phone: TextFieldState = TextFieldState(),
        val password: TextFieldState = TextFieldState(),
        val rememberMe: Boolean = false,
    )
}