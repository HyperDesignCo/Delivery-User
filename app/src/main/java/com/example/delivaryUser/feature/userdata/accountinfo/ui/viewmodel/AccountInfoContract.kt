package com.example.delivaryUser.feature.userdata.accountinfo.ui.viewmodel

sealed interface AccountInfoContract {
    sealed interface Action{
        data object Init : Action
        data object OnEditClicked : Action
        data object OnSaveClicked : Action
        data object OnBackClicked : Action
    }
    data class State(
        val name : String ="",
        val phoneNumber : String = "",
        val password: String = ""
    )
}