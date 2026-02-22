package com.example.delivaryUser.feature.userdata.selectlanguage.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.example.delivaryUser.service.language.domain.usecase.AddLanguageUseCase
import com.example.delivaryUser.service.language.domain.usecase.GetLanguageUseCase
import kotlinx.coroutines.launch

class SelectLanguageViewModel(val language: GetLanguageUseCase, val saveLanguage: AddLanguageUseCase) :
    BaseViewModel<SelectLanguageContract.State, SelectLanguageContract.Action>(SelectLanguageContract.State()) {
    init {
        getLanguage()
    }
    override fun onActionTrigger(action: SelectLanguageContract.Action) {
        when (action) {
            SelectLanguageContract.Action.OnApplyClicked -> onApplyClicked()
            SelectLanguageContract.Action.OnArabicClicked -> onArabicSelected()
            SelectLanguageContract.Action.OnEnglishClicked -> onEnglishSelected()
            SelectLanguageContract.Action.OnBackClicked -> onBackClicked()
        }
    }

    private fun getLanguage() {
        viewModelScope.launch {
            language.invoke(body = Unit).collectResource(onSuccess = ::onGetLanguageSuccess)
        }
    }

    private fun onGetLanguageSuccess(language: String) {
        updateState {
            copy(selectedLanguage = language, isEnglishSelected = language == "en", isArabicSelected = language == "ar")
        }
    }
    private fun onArabicSelected(){
        updateState {
            copy(isEnglishSelected = false, isArabicSelected = true, selectedLanguage = "ar")
        }
    }
    private fun onEnglishSelected(){
        updateState {
            copy(isEnglishSelected = true, isArabicSelected = false, selectedLanguage = "en")
        }
    }
    private fun onApplyClicked(){
        viewModelScope.launch {
            saveLanguage.invoke(body = state.value.selectedLanguage).collectResource(onSuccess = {        fireLanguageEvent(language= state.value.selectedLanguage)})
        }
    }
    private fun onBackClicked() {
        viewModelScope.launch {
            fireNavigateUp()
        }
    }
}