package com.example.delivaryUser.feature.userdata.account.ui.viewmodel

sealed interface AccountScreenContract {
    sealed interface Action{
        object Init : Action
        object OnEditProfileClicked : Action
        object OnMyOrdersClicked : Action
        object OnGetHelpClicked : Action
        object OnAccountInfoClicked : Action
        object OnSavedLocationClicked : Action
        object OnLanguageClicked : Action
        object OnLogoutClicked : Action
    }
    data class State(
        val name : String = "",
        val image: String = "",
        val id: Int = 0
    )
}