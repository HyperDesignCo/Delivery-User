package com.hyperdesign.delivaryUser.feature.authentication.changepassword.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.data.repository.remote.ErrorKeyEnum
import com.hyperdesign.delivaryUser.common.domain.exceptions.IErrorKeyEnum
import com.hyperdesign.delivaryUser.common.ui.extension.UIText
import com.hyperdesign.delivaryUser.common.ui.loading.ILoadingEvent
import com.hyperdesign.delivaryUser.common.ui.message.IMessageEvent
import com.hyperdesign.delivaryUser.common.ui.message.MessageType
import com.hyperdesign.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.hyperdesign.delivaryUser.feature.authentication.changepassword.data.model.request.ChangePasswordRequest
import com.hyperdesign.delivaryUser.feature.authentication.changepassword.domain.interactors.ChangePasswordUseCase
import kotlinx.coroutines.launch

class ChangePasswordViewModel(private val useCase: ChangePasswordUseCase) :
    BaseViewModel<ChangePasswordContract.State, ChangePasswordContract.Action>(ChangePasswordContract.State()) {
    override fun onActionTrigger(action: ChangePasswordContract.Action) {
        when (action) {
            is ChangePasswordContract.Action.OnConfirmationPasswordChanged -> onConfirmationPassword(action.password)
            is ChangePasswordContract.Action.OnNewPasswordChanged -> onNewPasswordChanged(action.password)
            is ChangePasswordContract.Action.OnSendClicked -> changePassword()
        }
    }

    private fun onNewPasswordChanged(value: String) =
        updateState { copy(newPassword = newPassword.copy(value = value, error = null)) }

    private fun onConfirmationPassword(value: String) =
        updateState { copy(confirmationPassword = confirmationPassword.copy(value = value, error = null)) }

    private fun changePassword() {
        val request = ChangePasswordRequest(
            password = state.value.newPassword.value,
            confirmPassword = state.value.confirmationPassword.value
        )
        viewModelScope.launch {
            useCase.invoke(body = request).collectResource(
                onSuccess = {
                    fireMessage(
                        IMessageEvent.Snackbar(
                            UIText.StringResource(R.string.password_changed_successfully),
                            messageType = MessageType.SUCCESS
                        )
                    )
                    fireNavigateUp()
                },
                onLoading = {
                    fireLoading(ILoadingEvent.CircularProgressIndicator(isLoading = it))
                }
            )
        }
    }

    override fun onRequestValidation(errors: Map<IErrorKeyEnum, UIText>) = updateState {
        copy(
            newPassword = newPassword.copy(error = errors[ErrorKeyEnum.PASSWORD]),
            confirmationPassword = confirmationPassword.copy(error = errors[ErrorKeyEnum.CONFIRMATION_PASSWORD]),
        )
    }
}