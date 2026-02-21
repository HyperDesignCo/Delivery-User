package com.example.delivaryUser.feature.chatwithdelivery.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.delivaryUser.common.ui.loading.ILoadingEvent
import com.example.delivaryUser.common.ui.navigation.IMainGraph
import com.example.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.example.delivaryUser.feature.chatwithdelivery.data.model.request.UserAddMessageRequest
import com.example.delivaryUser.feature.chatwithdelivery.data.model.request.UserChatRequest
import com.example.delivaryUser.feature.chatwithdelivery.domain.interactors.GetUserChatUseCase
import com.example.delivaryUser.feature.chatwithdelivery.domain.interactors.SendMessageUseCase
import com.example.delivaryUser.feature.chatwithdelivery.domain.model.UserChat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ChatWithDeliveryViewModel(
    private val getUserChatUseCase: GetUserChatUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<ChatWithDeliveryContract.State, ChatWithDeliveryContract.Action>(
    ChatWithDeliveryContract.State()
) {
    private val _effect = Channel<String>()
    val effect = _effect.receiveAsFlow()

    private var chatIdVar: Int = 0
    private var deliveryIdVar: Int = 0
    val route = savedStateHandle.toRoute<IMainGraph.ChatWithDelivery>()

    init {
        val orderId = route.orderId.parseToInt()
        val chatId = route.chatId.parseToInt()
        val deliveryId = route.deliveryId.parseToInt()

        this.chatIdVar = chatId
        this.deliveryIdVar = deliveryId

        initializeChat(
            orderId = orderId, chatId = chatId, isNewChat = route.isNewChat
        )
    }

    override fun onActionTrigger(action: ChatWithDeliveryContract.Action) {
        when (action) {
            is ChatWithDeliveryContract.Action.MessageChanged -> {
                updateState {
                    copy(message = message.copy(value = action.message, error = null))
                }
            }

            ChatWithDeliveryContract.Action.NavigateBacKClicked -> {
                navigateBackClicked()
            }

            ChatWithDeliveryContract.Action.SendMessageClicked -> {
                sendMessageClicked()
            }

            ChatWithDeliveryContract.Action.OnCallDriverClicked -> onCallDriverClicked()
        }
    }

    fun initializeChat(
        orderId: Int,
        chatId: Int,
        isNewChat: Boolean = false,
    ) {
        viewModelScope.launch(Dispatchers.IO) {

            val chatRequest = UserChatRequest(
                orderId = orderId,
                type = if (isNewChat) "new" else "old",
                chatId = if (!isNewChat) chatId else 0,
            )

            getUserChatUseCase(chatRequest).collectResource(
                onSuccess = ::getUserChatSuccess, onLoading = {
                    fireLoading(ILoadingEvent.CircularProgressIndicator(isLoading = it))
                })
        }
    }

    private fun navigateBackClicked() = viewModelScope.launch {
        fireNavigateUp()
    }

    private fun sendMessageClicked() {
        val messageText = state.value.message.value

        if (messageText.isBlank()) {
            return
        }

        val addMessageRequest = UserAddMessageRequest(
            chatId = if (route.isNewChat) state.value.chatId else chatIdVar,
            deliveryId = deliveryIdVar,
            message = messageText
        )

        viewModelScope.launch {
            sendMessageUseCase(addMessageRequest).collectResource(
                onSuccess = ::getUserChatSuccess, onLoading = {
                    fireLoading(ILoadingEvent.CircularProgressIndicator(isLoading = it))
                })
        }
    }

    private fun onCallDriverClicked() = viewModelScope.launch {
        val phoneNumber = state.value.deliveryNumber
        if (phoneNumber.isNotEmpty()) {
            _effect.send(phoneNumber)
        }
    }

    private fun getUserChatSuccess(model: UserChat) {
        updateState {
            copy(
                message = message.copy(value = ""),
                deliveryImage = route.deliveryImg,
                deliveryName = route.deliveryName,
                deliveryNumber = route.deliveryNumber,
                chatId = model.chatId,
                chatMessages = model.chatMessages.map { it.toUiState() })
        }
    }

    private fun Any?.parseToInt(): Int {
        return when {
            this == null -> 0
            this is Int -> this
            this is String -> {
                this.trim().filter { it.isDigit() }.toIntOrNull() ?: 0
            }
            else -> 0
        }
    }
}