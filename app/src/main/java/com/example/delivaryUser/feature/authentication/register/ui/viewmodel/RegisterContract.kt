package com.example.delivaryUser.feature.authentication.register.ui.viewmodel

import com.example.delivaryUser.common.ui.filedstate.TextFieldState

interface RegisterContract {
    sealed interface Action {
        data class NameChanged(val name: String) : Action
        data class EmailChanged(val email: String) : Action
        data class PhoneChanged(val phoneNumber: String) : Action
        data class PasswordChanged(val password: String) : Action
        data object LoginClicked : Action
        data object RegisterClicked : Action
    }

    data class State(
        val name: TextFieldState = TextFieldState(),
        val email: TextFieldState = TextFieldState(),
        val phone: TextFieldState = TextFieldState(),
        val password: TextFieldState = TextFieldState(),
    )
}