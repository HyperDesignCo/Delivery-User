package com.example.delivaryUser.feature.userdata.account.di

import com.example.delivaryUser.feature.userdata.account.domain.interactors.LogOutUseCase
import com.example.delivaryUser.feature.userdata.account.ui.viewmodel.AccountViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val accountModule = module {
    factoryOf(::LogOutUseCase)
    factoryOf(::AccountViewModel)
}