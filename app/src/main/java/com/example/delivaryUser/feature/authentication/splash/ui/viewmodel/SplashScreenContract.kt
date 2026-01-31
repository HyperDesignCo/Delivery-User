package com.example.delivaryUser.feature.authentication.splash.ui.viewmodel

interface SplashScreenContract {
    sealed interface Action {
        data object Init : Action
    }
}