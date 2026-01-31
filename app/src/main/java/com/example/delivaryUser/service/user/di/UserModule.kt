package com.example.delivaryUser.service.user.di

import com.example.delivaryUser.service.user.data.repository.UserRepository
import com.example.delivaryUser.service.user.data.repository.local.UserLocalDataSource
import com.example.delivaryUser.service.user.domain.interactors.GetIsAuthenticatedUseCase
import com.example.delivaryUser.service.user.domain.interactors.SaveIsAuthenticatedUseCase
import com.example.delivaryUser.service.user.domain.repository.IUserRepository
import com.example.delivaryUser.service.user.domain.repository.local.IUserLocalDataSource
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val userModule = module {
    singleOf(::UserLocalDataSource) bind IUserLocalDataSource::class
    singleOf(::UserRepository) bind IUserRepository::class
    factoryOf(::GetIsAuthenticatedUseCase)
    factoryOf(::SaveIsAuthenticatedUseCase)
}