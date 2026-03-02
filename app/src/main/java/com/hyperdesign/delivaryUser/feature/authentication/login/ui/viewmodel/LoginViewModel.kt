package com.hyperdesign.delivaryUser.feature.authentication.login.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.data.repository.remote.ErrorKeyEnum
import com.hyperdesign.delivaryUser.common.domain.exceptions.IErrorKeyEnum
import com.hyperdesign.delivaryUser.common.ui.extension.UIText
import com.hyperdesign.delivaryUser.common.ui.loading.ILoadingEvent
import com.hyperdesign.delivaryUser.common.ui.message.IMessageEvent
import com.hyperdesign.delivaryUser.common.ui.message.MessageType
import com.hyperdesign.delivaryUser.common.ui.navigation.IAuthGraph
import com.hyperdesign.delivaryUser.common.ui.navigation.IMainGraph
import com.hyperdesign.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.hyperdesign.delivaryUser.feature.authentication.login.data.models.request.LoginRequest
import com.hyperdesign.delivaryUser.feature.authentication.login.data.models.request.SocialLoginRequest
import com.hyperdesign.delivaryUser.feature.authentication.login.domain.interactors.LoginUseCase
import com.hyperdesign.delivaryUser.feature.authentication.login.domain.interactors.SocialLoginUseCase
import com.hyperdesign.delivaryUser.feature.authentication.login.domain.models.GoogleSignInResult
import com.hyperdesign.delivaryUser.service.fcm.FCMManager
import kotlinx.coroutines.launch

class LoginViewModel(
    private val useCase: LoginUseCase,
    private val socialLoginUseCase: SocialLoginUseCase,
) : BaseViewModel<LoginContract.State, LoginContract.Action>(
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
            is LoginContract.Action.GoogleSignInClicked -> googleSignInClicked(action.googleRequest)
            is LoginContract.Action.ShowGoogleSignInError -> showGoogleSignInError()
        }
    }

    private fun passwordChanged(value: String) =
        updateState { copy(password = password.copy(value = value, error = null)) }

    private fun phoneChanged(value: String) = updateState { copy(phone = phone.copy(value = value, error = null)) }
    private fun rememberMeClicked() = updateState { copy(rememberMe = rememberMe.not()) }
    private fun registerClicked() = fireNavigate(IAuthGraph.Register)
    private fun loginClicked() {
        viewModelScope.launch {
            fireLoading(ILoadingEvent.CircularProgressIndicator(isLoading = true))
            val deviceToken = FCMManager.getDeviceToken() ?: ""
            val request = LoginRequest(
                phone = state.value.phone.value,
                password = state.value.password.value,
                deviceToken = deviceToken,
                deviceType = "android",
                rememberMe = state.value.rememberMe,
            )
            useCase.invoke(body = request).collectResource(
                onSuccess = { loginSuccess() },
                onLoading = {
                    fireLoading(ILoadingEvent.CircularProgressIndicator(isLoading = it))
                }
            )
        }
    }

    private fun googleSignInClicked(googleSignInResult: GoogleSignInResult) {
        viewModelScope.launch {
            fireLoading(ILoadingEvent.CircularProgressIndicator(isLoading = true))
            val deviceToken = FCMManager.getDeviceToken().orEmpty()
            val request = SocialLoginRequest(
                email = googleSignInResult.email,
                name = googleSignInResult.name,
                deviceToken = deviceToken,
                social = "google",
                socialId = googleSignInResult.socialId
            )
            socialLoginUseCase.invoke(body = request).collectResource(
                onSuccess = {
                    fireNavigate(IAuthGraph.VerifyPhone)
                },
                onLoading = {
                    fireLoading(ILoadingEvent.CircularProgressIndicator(isLoading = it))
                }
            )
        }
    }

    private fun loginSuccess() {
        fireMessage(
            IMessageEvent.Snackbar(
                UIText.StringResource(R.string.welcome_back),
                messageType = MessageType.SUCCESS
            )
        )
        navigateToHome()
    }

    private fun forgotPasswordClicked() = fireNavigate(IAuthGraph.ForgetPassword)
    private fun navigateToHome() = fireNavigate(IMainGraph.Home, builder = {
        popUpTo(0) {
            saveState = true
            inclusive = true
        }
    })
    private fun showGoogleSignInError() =  fireMessage(
        IMessageEvent.Snackbar(
            UIText.StringResource(R.string.something_wrong),
            messageType = MessageType.ERROR
        )
    )
    override fun onRequestValidation(errors: Map<IErrorKeyEnum, UIText>) = updateState {
        copy(
            phone = phone.copy(error = errors[ErrorKeyEnum.PHONE_NUMBER]),
            password = password.copy(error = errors[ErrorKeyEnum.PASSWORD])
        )
    }
}