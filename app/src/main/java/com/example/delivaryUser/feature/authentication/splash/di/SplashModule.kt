package com.example.delivaryUser.feature.authentication.splash.di

import com.example.delivaryUser.feature.authentication.splash.ui.viewmodel.SplashScreenViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val splashModule = module {
    factoryOf(::SplashScreenViewModel)
}