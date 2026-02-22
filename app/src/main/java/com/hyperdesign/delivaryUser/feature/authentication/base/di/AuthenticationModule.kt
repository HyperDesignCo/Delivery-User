package com.hyperdesign.delivaryUser.feature.authentication.base.di

import com.hyperdesign.delivaryUser.feature.authentication.base.data.repository.AuthenticationRepository
import com.hyperdesign.delivaryUser.feature.authentication.base.data.repository.remote.AuthenticationRemoteDataSource
import com.hyperdesign.delivaryUser.feature.authentication.base.domain.repository.IAuthenticationRepository
import com.hyperdesign.delivaryUser.feature.authentication.base.domain.repository.remote.IAuthenticationRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authenticationModule = module {
    singleOf(::AuthenticationRepository) bind IAuthenticationRepository::class
    singleOf(::AuthenticationRemoteDataSource) bind IAuthenticationRemoteDataSource::class
}