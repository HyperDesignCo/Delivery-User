package com.example.delivaryUser.feature.authentication.register.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.delivaryUser.common.data.repository.remote.ErrorKeyEnum
import com.example.delivaryUser.common.domain.exceptions.IErrorKeyEnum
import com.example.delivaryUser.common.ui.extension.UIText
import com.example.delivaryUser.common.ui.loading.ILoadingEvent
import com.example.delivaryUser.common.ui.navigation.IAuthGraph
import com.example.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.example.delivaryUser.feature.authentication.register.data.models.request.RegisterRequest
import com.example.delivaryUser.feature.authentication.register.domain.interactors.RegisterUseCase
import kotlinx.coroutines.launch

class RegisterViewModel(private val useCase: RegisterUseCase) :
    BaseViewModel<RegisterContract.State, RegisterContract.Action>(
        state = RegisterContract.State()
    ) {
    override fun onActionTrigger(action: RegisterContract.Action) {
        when (action) {
            is RegisterContract.Action.EmailChanged -> emailChanged(action.email)
            is RegisterContract.Action.NameChanged -> nameChanged(action.name)
            is RegisterContract.Action.PasswordChanged -> passwordChanged(action.password)
            is RegisterContract.Action.PhoneChanged -> phoneChanged(action.phoneNumber)
            is RegisterContract.Action.LoginClicked -> loginClicked()
            is RegisterContract.Action.RegisterClicked -> registerClicked()
        }
    }

    private fun emailChanged(value: String) =
        updateState { copy(email = email.copy(value = value, error = null)) }

    private fun nameChanged(value: String) =
        updateState { copy(name = name.copy(value = value, error = null)) }

    private fun passwordChanged(value: String) =
        updateState { copy(password = password.copy(value = value, error = null)) }

    private fun phoneChanged(value: String) = updateState { copy(phone = phone.copy(value = value, error = null)) }

    private fun registerClicked() {
        val request = RegisterRequest(
            phone = state.value.phone.value,
            password = state.value.password.value,
            email = state.value.email.value,
            name = state.value.name.value,
        )
        viewModelScope.launch {
            useCase.invoke(body = request).collectResource(
                onSuccess = {
                    fireNavigate(IAuthGraph.VerifyOtp(phone = state.value.phone.value))
                },
                onLoading = {
                    fireLoading(ILoadingEvent.CircularProgressIndicator(isLoading = it))
                }
            )
        }
    }

    private fun loginClicked() = fireNavigate(IAuthGraph.Login)

    override fun onRequestValidation(errors: Map<IErrorKeyEnum, UIText>) = updateState {
        copy(
            phone = phone.copy(error = errors[ErrorKeyEnum.PHONE_NUMBER]),
            password = password.copy(error = errors[ErrorKeyEnum.PASSWORD]),
            email = email.copy(error = errors[ErrorKeyEnum.EMAIL]),
            name = name.copy(error = errors[ErrorKeyEnum.NAME]),
        )
    }
}