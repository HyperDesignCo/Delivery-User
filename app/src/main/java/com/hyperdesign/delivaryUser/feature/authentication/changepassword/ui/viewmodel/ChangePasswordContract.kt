package com.hyperdesign.delivaryUser.feature.authentication.changepassword.ui.viewmodel

import com.hyperdesign.delivaryUser.common.ui.filedstate.TextFieldState

sealed interface ChangePasswordContract {
    sealed interface Action {
        data class OnNewPasswordChanged(val password: String) : Action
        data class OnConfirmationPasswordChanged(val password: String) : Action
        data object OnSendClicked : Action
    }

    data class State(
        val newPassword: TextFieldState = TextFieldState(),
        val confirmationPassword: TextFieldState = TextFieldState(),
    )
}