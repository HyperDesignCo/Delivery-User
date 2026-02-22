package com.example.delivaryUser.feature.chatwithai.ui.viewmodel

sealed interface ChatWithAiContract {
    sealed interface Action : ChatWithAiContract {
        data object OnBackClicked : Action
        data object Init : Action
    }

    data class State(
        val link: String = "",
    ) : ChatWithAiContract
}