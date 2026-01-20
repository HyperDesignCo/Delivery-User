package com.example.delivaryUser.feature.authentication.verifyOtp.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.delivaryUser.common.domain.exceptions.IErrorKeyEnum
import com.example.delivaryUser.common.ui.extension.UIText
import com.example.delivaryUser.common.ui.loading.ILoadingEvent
import com.example.delivaryUser.common.ui.message.IMessageEvent
import com.example.delivaryUser.common.ui.navigation.IMainGraph
import com.example.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.example.delivaryUser.feature.authentication.verifyOtp.data.models.request.VerifyOtpRequest
import com.example.delivaryUser.feature.authentication.verifyOtp.domain.interactors.ResendCodeUseCase
import com.example.delivaryUser.feature.authentication.verifyOtp.domain.interactors.VerifyOtpUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class VerifyOtpViewModel(private val verify: VerifyOtpUseCase, private val resend: ResendCodeUseCase) :
    BaseViewModel<VerifyOtpContract.State, VerifyOtpContract.Action>(
        state = VerifyOtpContract.State()
    ) {

    init {
        startTimer()
    }

    override fun onActionTrigger(action: VerifyOtpContract.Action) {
        when (action) {
            is VerifyOtpContract.Action.FirstDigitChanged -> firstDigitChanged(action.digit)
            is VerifyOtpContract.Action.SecondDigitChanged -> secondDigitChanged(action.digit)
            is VerifyOtpContract.Action.ThirdDigitChanged -> thirdDigitChanged(action.digit)
            is VerifyOtpContract.Action.FourthDigitChanged -> fourthDigitChanged(action.digit)
            is VerifyOtpContract.Action.VerifyClicked -> verifyClicked()
            is VerifyOtpContract.Action.ResendClicked -> resendClicked()
        }
    }

    private fun firstDigitChanged(value: String) =
        updateState { copy(firstDigit = firstDigit.copy(value = value.filterOtp(), error = null)) }

    private fun secondDigitChanged(value: String) =
        updateState { copy(secondDigit = secondDigit.copy(value = value.filterOtp(), error = null)) }

    private fun thirdDigitChanged(value: String) =
        updateState { copy(thirdDigit = thirdDigit.copy(value = value.filterOtp(), error = null)) }

    private fun fourthDigitChanged(value: String) =
        updateState { copy(fourthDigit = fourthDigit.copy(value = value.filterOtp(), error = null)) }

    private fun verifyClicked() {
        val request = VerifyOtpRequest(
            code = state.value.code
        )
        viewModelScope.launch {
            verify.invoke(body = request).collectResource(
                onSuccess = {
                    fireMessage(IMessageEvent.Toast(message = UIText.DynamicString(it.message)))
                    startTimer()
                    navigateToHome()
                },
                onLoading = {
                    fireLoading(ILoadingEvent.CircularProgressIndicator(isLoading = it))
                }
            )
        }
    }

    private fun resendClicked() {
        viewModelScope.launch {
            resend.invoke(body = Unit).collectResource(
                onSuccess = {
                    fireMessage(IMessageEvent.Toast(message = UIText.DynamicString(it.message)))
                },
                onLoading = {
                    fireLoading(ILoadingEvent.CircularProgressIndicator(isLoading = it))
                }
            )
        }
    }

    private fun String.filterOtp() = this.filter { it.isDigit() }.take(1)
    private fun startTimer() {
        viewModelScope.launch {
            for (i in 60 downTo 0) {
                updateState { copy(timer = i.toString()) }
                delay(1000)
            }
            if (state.value.timer == "0") {
                updateState { copy(isResendEnabled = true, timer = "") }
            } else {
                updateState { copy(isResendEnabled = false) }
            }
        }
    }

    private fun navigateToHome() = fireNavigate(IMainGraph.Home, builder = {
        popUpTo(0) {
            saveState = true
            inclusive = true
        }
    })

    override fun onRequestValidation(errors: Map<IErrorKeyEnum, UIText>) {
        super.onRequestValidation(errors)
        errors.forEach { (_, errorMessage) ->
            fireMessage(IMessageEvent.Toast(message = errorMessage))
            startTimer()
        }
    }
}