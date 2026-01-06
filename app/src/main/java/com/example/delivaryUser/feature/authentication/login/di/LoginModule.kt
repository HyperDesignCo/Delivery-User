package com.example.delivaryUser.feature.authentication.login.di

import com.example.delivaryUser.feature.authentication.login.domain.interactors.LoginUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val loginModule = module {
    factoryOf(::LoginUseCase)
}