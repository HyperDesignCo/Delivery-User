package com.example.delivaryUser.feature.authentication.changepassword.di

import com.example.delivaryUser.feature.authentication.changepassword.data.repository.ChangePasswordRepository
import com.example.delivaryUser.feature.authentication.changepassword.data.repository.remote.ChangePasswordRemoteDataSource
import com.example.delivaryUser.feature.authentication.changepassword.domain.interactors.ChangePasswordUseCase
import com.example.delivaryUser.feature.authentication.changepassword.domain.repository.IChangePasswordRepository
import com.example.delivaryUser.feature.authentication.changepassword.domain.repository.remote.IChangePasswordRemoteDataSource
import com.example.delivaryUser.feature.authentication.changepassword.ui.viewmodel.ChangePasswordViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val changePasswordModule = module {
    singleOf(::ChangePasswordRepository) bind IChangePasswordRepository::class
    singleOf(::ChangePasswordRemoteDataSource) bind IChangePasswordRemoteDataSource::class
    factoryOf(::ChangePasswordUseCase)
    factoryOf(::ChangePasswordViewModel)
}