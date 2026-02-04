package com.example.delivaryUser.common.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptionsBuilder
import com.example.delivaryUser.R
import com.example.delivaryUser.common.data.DelivaryUserException
import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.exceptions.IErrorKeyEnum
import com.example.delivaryUser.common.domain.exceptions.RequestErrorKeyValues
import com.example.delivaryUser.common.ui.eventcontroller.IEventController
import com.example.delivaryUser.common.ui.extension.UIText
import com.example.delivaryUser.common.ui.language.ILanguageEvent
import com.example.delivaryUser.common.ui.loading.ILoadingEvent
import com.example.delivaryUser.common.ui.message.IMessageEvent
import com.example.delivaryUser.common.ui.navigation.IDestination
import com.example.delivaryUser.common.ui.navigation.INavigator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

abstract class BaseViewModel<State, Action>(state: State) : ViewModel(), KoinComponent {
    private val _state = MutableStateFlow(state)
    val state = _state.asStateFlow()
    private val navigator: INavigator by inject()
    private val messageEvent: IEventController<IMessageEvent> by inject(named("MessageEvent"))
    private val loadingEvent: IEventController<ILoadingEvent> by inject(named("LoadingEvent"))
    private val languageEvent: IEventController<ILanguageEvent> by inject(named("LanguageEvent"))
    abstract fun onActionTrigger(action: Action)
    fun updateState(update: State.() -> State) {
        _state.update { it.update() }
    }

    fun fireLoading(loadingType: ILoadingEvent) = viewModelScope.launch { loadingEvent.emit(loadingType) }

    fun fireMessage(messageType: IMessageEvent) = viewModelScope.launch { messageEvent.emit(messageType) }

    fun fireNavigate(destination: IDestination, builder: NavOptionsBuilder.() -> Unit = {}) =
        viewModelScope.launch { navigator.navigate(destination, builder = builder) }

    suspend fun fireNavigateUp() {
        navigator.navigateUp()
    }
    fun fireLanguageEvent(language: String) {
        viewModelScope.launch {
            languageEvent.emit(ILanguageEvent.ChangeLanguage(language))
        }
    }
    fun <Result> Flow<Resource<Result>>.collectResource(
        onSuccess: suspend (Result) -> Unit = {},
        onFailure: suspend (DelivaryUserException) -> Unit = {},
        onLoading: suspend (Boolean) -> Unit = {},
    ) = viewModelScope.launch {
        this@collectResource.collect { resource ->
            when (resource) {
                is Resource.Failure -> {
                    handleExceptions(resource.exception, ::onRequestValidation)
                    onFailure(resource.exception)
                }

                is Resource.Loading -> onLoading(resource.isLoading)
                is Resource.Success -> onSuccess(resource.model)
            }
        }
    }

    private fun handleExceptions(
        exception: DelivaryUserException,
        onRequestValidation: (Map<IErrorKeyEnum, UIText>) -> Unit = {},
    ) {
        when (exception) {
            is DelivaryUserException.Client.ResponseValidation -> onRequestValidation(
                exception.errors.mapValues { UIText.DynamicString(it.value) }
            )

            is DelivaryUserException.Client.UnAuthorized -> {
                // TODO Handle UnAuthorized
            }

            is DelivaryUserException.Client.Unhandled -> handleExceptionMessages(message = exception.message)

            is DelivaryUserException.Local.IOOperation -> handleExceptionMessages(message = exception.message)

            is DelivaryUserException.Local.RequestValidation -> {
                onRequestValidation(
                    exception.errors
                        .mapValues { requestErrorMap[it.value] ?: UIText.StringResource(R.string.unknown_error) }
                )
            }

            is DelivaryUserException.Local.Unhandled -> handleExceptionMessages(message = exception.message)

            is DelivaryUserException.Network.Repeatable -> handleExceptionMessages(message = exception.message)

            is DelivaryUserException.Network.Unhandled -> handleExceptionMessages(message = exception.message)

            is DelivaryUserException.Server.InternalServerError -> handleExceptionMessages(message = exception.message)

            is DelivaryUserException.UnKnown -> handleExceptionMessages(message = exception.message)
        }
    }

    open fun onRequestValidation(errors: Map<IErrorKeyEnum, UIText>) {}

    private fun handleExceptionMessages(message: String?) = fireMessage(
        messageType = IMessageEvent.Toast(
            message = message?.let { UIText.DynamicString(it) }
                ?: UIText.StringResource(R.string.something_wrong)
        )
    )

    companion object {
        private val requestErrorMap = mapOf<RequestErrorKeyValues, UIText>(
            RequestErrorKeyValues.PASSWORD_VALIDATION to UIText.StringResource(R.string.password_validation_message),
            RequestErrorKeyValues.PHONE_VALIDATION to UIText.StringResource(R.string.phone_validation_message),
            RequestErrorKeyValues.NAME_VALIDATION to UIText.StringResource(R.string.user_name_validation_message),
            RequestErrorKeyValues.EMAIL_VALIDATION to UIText.StringResource(R.string.email_validation_message),
            RequestErrorKeyValues.OTP_VALIDATION to UIText.StringResource(R.string.otp_validation_message) ,
            RequestErrorKeyValues.CONFIRMATION_PASSWORD to UIText.StringResource(R.string.confirmation_password_does_not_match)
        )
    }
}