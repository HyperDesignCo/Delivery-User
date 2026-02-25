package com.hyperdesign.delivaryUser.feature.authentication.verifyphone.ui.viewmodel

import com.hyperdesign.delivaryUser.common.ui.filedstate.TextFieldState

sealed interface VerifyPhoneContract {
    sealed interface Action {
        data object SendOtpClicked : Action
        data class PhoneChanged(val phone: String) : Action
    }

    data class State(
        val phone: TextFieldState = TextFieldState(),
    )
}