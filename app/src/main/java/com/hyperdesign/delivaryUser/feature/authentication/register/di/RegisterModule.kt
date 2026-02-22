package com.hyperdesign.delivaryUser.feature.authentication.register.di

import com.hyperdesign.delivaryUser.feature.authentication.register.domain.interactors.RegisterUseCase
import com.hyperdesign.delivaryUser.feature.authentication.register.ui.viewmodel.RegisterViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val registerModule = module {
    factoryOf(::RegisterUseCase)
    viewModelOf(::RegisterViewModel)
}