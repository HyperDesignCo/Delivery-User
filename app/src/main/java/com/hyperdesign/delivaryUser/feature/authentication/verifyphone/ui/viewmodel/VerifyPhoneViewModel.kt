package com.hyperdesign.delivaryUser.feature.authentication.verifyphone.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.hyperdesign.delivaryUser.common.domain.exceptions.IErrorKeyEnum
import com.hyperdesign.delivaryUser.common.ui.extension.UIText
import com.hyperdesign.delivaryUser.common.ui.loading.ILoadingEvent
import com.hyperdesign.delivaryUser.common.ui.message.IMessageEvent
import com.hyperdesign.delivaryUser.common.ui.message.MessageType
import com.hyperdesign.delivaryUser.common.ui.navigation.IAuthGraph
import com.hyperdesign.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.hyperdesign.delivaryUser.feature.authentication.verifyphone.data.model.request.VerifyPhoneRequest
import com.hyperdesign.delivaryUser.feature.authentication.verifyphone.domain.interactors.VerifyPhoneUseCase
import kotlinx.coroutines.launch

class VerifyPhoneViewModel(private val useCase: VerifyPhoneUseCase) :
    BaseViewModel<VerifyPhoneContract.State, VerifyPhoneContract.Action>(VerifyPhoneContract.State()) {
    override fun onActionTrigger(action: VerifyPhoneContract.Action) {
        when (action) {
            is VerifyPhoneContract.Action.PhoneChanged -> phoneChanged(action.phone)
            is VerifyPhoneContract.Action.SendOtpClicked -> verifyPhone()
        }
    }

    private fun phoneChanged(value: String) =
        updateState { copy(phone = phone.copy(value = value, error = null)) }

    private fun verifyPhone() {
        val request = VerifyPhoneRequest(
            phone = state.value.phone.value
        )
        viewModelScope.launch {
            useCase.invoke(body = request).collectResource(
                onSuccess = {
                    fireMessage(
                        IMessageEvent.Snackbar(
                            UIText.DynamicString(it),
                            messageType = MessageType.SUCCESS
                        )
                    )
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