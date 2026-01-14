package com.example.delivaryUser.feature.authentication.register.di

import com.example.delivaryUser.feature.authentication.register.domain.interactors.RegisterUseCase
import com.example.delivaryUser.feature.authentication.register.ui.viewmodel.RegisterViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val registerModule = module {
    factoryOf(::RegisterUseCase)
    viewModelOf(::RegisterViewModel)
}