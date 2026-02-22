package com.example.delivaryUser.feature.userdata.selectlanguage.ui.viewmodel

sealed interface SelectLanguageContract {
    sealed interface Action {
        data object OnEnglishClicked : Action
        data object OnArabicClicked: Action
        data object OnApplyClicked: Action
        data object OnBackClicked : Action
    }
    data class State(
        val selectedLanguage: String = "",
        val isEnglishSelected : Boolean = false,
        val isArabicSelected : Boolean= false
    )
}