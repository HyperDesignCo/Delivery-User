package com.example.delivaryUser.feature.authentication.verifyOtp.ui.viewmodel

import com.example.delivaryUser.common.ui.filedstate.TextFieldState

interface VerifyOtpContract {
    sealed interface Action {
        data class FirstDigitChanged(val digit: String) : Action
        data class SecondDigitChanged(val digit: String) : Action
        data class ThirdDigitChanged(val digit: String) : Action
        data class FourthDigitChanged(val digit: String) : Action
        data object VerifyClicked : Action
        data object ResendClicked : Action
    }

    data class State(
        val firstDigit: TextFieldState = TextFieldState(),
        val secondDigit: TextFieldState = TextFieldState(),
        val thirdDigit: TextFieldState = TextFieldState(),
        val fourthDigit: TextFieldState = TextFieldState(),
        val timer : String = "60",
        val isResendEnabled : Boolean = false
    ){
        val code : String
            get() =  firstDigit.value + secondDigit.value + thirdDigit.value + fourthDigit.value
    }
}