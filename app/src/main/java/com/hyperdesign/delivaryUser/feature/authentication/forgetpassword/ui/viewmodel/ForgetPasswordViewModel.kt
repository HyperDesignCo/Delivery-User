package com.hyperdesign.delivaryUser.feature.authentication.forgetpassword.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.hyperdesign.delivaryUser.common.domain.exceptions.IErrorKeyEnum
import com.hyperdesign.delivaryUser.common.ui.extension.UIText
import com.hyperdesign.delivaryUser.common.ui.loading.ILoadingEvent
import com.hyperdesign.delivaryUser.common.ui.message.IMessageEvent
import com.hyperdesign.delivaryUser.common.ui.message.MessageType
import com.hyperdesign.delivaryUser.common.ui.navigation.IAuthGraph
import com.hyperdesign.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.hyperdesign.delivaryUser.feature.authentication.forgetpassword.data.model.request.ForgetPasswordRequest
import com.hyperdesign.delivaryUser.feature.authentication.forgetpassword.domain.interactors.ForgetPasswordUseCase
import kotlinx.coroutines.launch

class ForgetPasswordViewModel(private val useCase: ForgetPasswordUseCase) :
    BaseViewModel<ForgetPasswordContract.State, ForgetPasswordContract.Action>(ForgetPasswordContract.State()) {
    override fun onActionTrigger(action: ForgetPasswordContract.Action) {
        when(action){
            is ForgetPasswordContract.Action.PhoneChanged -> phoneChanged(action.phone)
            is ForgetPasswordContract.Action.SendOtpClicked -> forgetPassword()
        }
    }

    private fun phoneChanged(value: String) =
        updateState { copy(phone = phone.copy(value = value, error = null)) }

    private fun forgetPassword() {
        val request = ForgetPasswordRequest(
            phone = state.value.phone.value
        )
        viewModelScope.launch {
            useCase.invoke(body = request).collectResource(
                onSuccess = {
                    fireMessage(IMessageEvent.Snackbar(
                        UIText.DynamicString(it.message),
                        messageType = MessageType.SUCCESS
                    ))
                    fireNavigate(IAuthGraph.VerifyOtp(phone = state.value.phone.value))
                },
                onLoading = {
                    fireLoading(ILoadingEvent.CircularProgressIndicator(isLoading = it))
                }
            )
        }
    }

    override fun onRequestValidation(errors: Map<IErrorKeyEnum, UIText>) {
        super.onRequestValidation(errors)
        errors.forEach { (_, errorMessage) ->
            fireMessage(IMessageEvent.Snackbar(message = errorMessage, messageType = MessageType.ERROR))
        }
    }
}