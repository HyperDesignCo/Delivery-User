package com.example.delivaryUser.common.ui.language

sealed interface ILanguageEvent {
    val languageCode : String
    data class ChangeLanguage(override val languageCode: String) : ILanguageEvent
}