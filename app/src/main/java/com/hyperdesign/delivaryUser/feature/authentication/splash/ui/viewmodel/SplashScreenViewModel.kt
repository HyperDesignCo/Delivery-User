package com.hyperdesign.delivaryUser.feature.authentication.splash.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.hyperdesign.delivaryUser.common.ui.navigation.IAuthGraph
import com.hyperdesign.delivaryUser.common.ui.navigation.IMainGraph
import com.hyperdesign.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.hyperdesign.delivaryUser.service.language.domain.usecase.AddLanguageUseCase
import com.hyperdesign.delivaryUser.service.language.domain.usecase.GetLanguageUseCase
import com.hyperdesign.delivaryUser.service.user.domain.interactors.GetIsAuthenticatedUseCase
import kotlinx.coroutines.launch
import java.util.Locale

class SplashScreenViewModel(
    private val getLanguage: GetLanguageUseCase,
    private val addLanguage: AddLanguageUseCase,
    private val getIsAuthenticatedUseCase: GetIsAuthenticatedUseCase,
) : BaseViewModel<Unit, SplashScreenContract.Action>(Unit) {
    init {
        onActionTrigger(SplashScreenContract.Action.Init)
    }

    override fun onActionTrigger(action: SplashScreenContract.Action) {
        when (action) {
            SplashScreenContract.Action.Init -> getLanguage()
        }
    }

    private fun getLanguage() =
        viewModelScope.launch {
            getLanguage.invoke(body = Unit).collectResource(onSuccess = { checkLanguage(lang = it) })
        }

    private fun checkLanguage(lang: String) =
        if (lang != getDefaultLanguage()) saveLanguage(lang) else getIsAuthenticated()

    private fun getDefaultLanguage(): String = if (Locale.getDefault().language == "ar") "ar" else "en"

    private fun saveLanguage(lang: String) =
        viewModelScope.launch {
            addLanguage.invoke(body = lang).collectResource {
                getIsAuthenticated()
            }
        }

    private fun getIsAuthenticated() =
        viewModelScope.launch {
            getIsAuthenticatedUseCase.invoke(Unit).collectResource(
                onSuccess = { if (it) navigateToHome() else navigateToLogin() }
            )
        }

    private fun navigateToHome() =
        fireNavigate(IMainGraph.Home, { popUpTo(IAuthGraph.Splash) { inclusive = true } })

    private fun navigateToLogin() =
        fireNavigate(IAuthGraph.Login, { popUpTo(IAuthGraph.Splash) { inclusive = true } })
}