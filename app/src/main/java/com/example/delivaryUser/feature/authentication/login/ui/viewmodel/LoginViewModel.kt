package com.example.delivaryUser.feature.authentication.login.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.delivaryUser.R
import com.example.delivaryUser.common.data.repository.remote.ErrorKeyEnum
import com.example.delivaryUser.common.domain.exceptions.IErrorKeyEnum
import com.example.delivaryUser.common.ui.extension.UIText
import com.example.delivaryUser.common.ui.loading.ILoadingEvent
import com.example.delivaryUser.common.ui.message.IMessageEvent
import com.example.delivaryUser.common.ui.navigation.IAuthGraph
import com.example.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.example.delivaryUser.feature.authentication.login.data.models.request.LoginRequest
import com.example.delivaryUser.feature.authentication.login.domain.interactors.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(private val useCase: LoginUseCase) : BaseViewModel<LoginContract.State, LoginContract.Action>(
    state = LoginContract.State()
) {
    override fun onActionTrigger(action: LoginContract.Action) {
        when (action) {
            is LoginContract.Action.PasswordChanged -> passwordChanged(action.password)
            is LoginContract.Action.PhoneChanged -> phoneChanged(action.phoneNumber)
            is LoginContract.Action.RememberMeClicked -> rememberMeClicked()
            is LoginContract.Action.RegisterClicked -> registerClicked()
            is LoginContract.Action.ForgotPasswordClicked -> forgotPasswordClicked()
            is LoginContract.Action.LoginClicked -> loginClicked()
        }
    }

    private fun passwordChanged(value: String) =
        updateState { copy(password = password.copy(value = value, error = null)) }

    private fun phoneChanged(value: String) = updateState { copy(phone = phone.copy(value = value, error = null)) }
    private fun rememberMeClicked() = updateState { copy(rememberMe = rememberMe.not()) }
    private fun registerClicked() = fireNavigate(IAuthGraph.Register)
    private fun loginClicked() {
        val request = LoginRequest(
            phone = state.value.phone.value,
            password = state.value.password.value,
            rememberMe = state.value.rememberMe
        )
        viewModelScope.launch {
            useCase.invoke(body = request).collectResource(
                onSuccess = {
                    fireMessage(IMessageEvent.Toast(UIText.StringResource(R.string.login)))
                },
                onLoading = {
                    fireLoading(ILoadingEvent.CircularProgressIndicator(isLoading = it))
                }
            )
        }
    }
    private fun forgotPasswordClicked() = fireNavigate(IAuthGraph.ForgetPassword)

    override fun onRequestValidation(errors: Map<IErrorKeyEnum, UIText>) = updateState {
        copy(
            phone = phone.copy(error = errors[ErrorKeyEnum.PHONE_NUMBER]),
            password = password.copy(error = errors[ErrorKeyEnum.PASSWORD])
        )
    }
}