package com.example.delivaryUser.feature.userdata.editaccount.di

import com.example.delivaryUser.feature.userdata.editaccount.data.repository.EditAccountRepository
import com.example.delivaryUser.feature.userdata.editaccount.data.repository.remote.EditAccountRemoteDataSource
import com.example.delivaryUser.feature.userdata.editaccount.domain.interactors.EditAccountUseCase
import com.example.delivaryUser.feature.userdata.editaccount.domain.repository.IEditAccountRepository
import com.example.delivaryUser.feature.userdata.editaccount.domain.repository.remote.IEditAccountRemoteDataSource
import com.example.delivaryUser.feature.userdata.editaccount.ui.viewmodel.EditAccountViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val editAccountModule = module {
    singleOf(::EditAccountRemoteDataSource) bind IEditAccountRemoteDataSource::class
    factoryOf(::EditAccountRepository) bind IEditAccountRepository::class
    factoryOf(::EditAccountUseCase)
    viewModelOf(::EditAccountViewModel)
}