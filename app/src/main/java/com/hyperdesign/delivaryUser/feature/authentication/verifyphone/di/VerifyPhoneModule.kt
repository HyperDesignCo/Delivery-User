package com.hyperdesign.delivaryUser.feature.authentication.verifyphone.di

import com.hyperdesign.delivaryUser.feature.authentication.verifyphone.data.repository.VerifyPhoneRepository
import com.hyperdesign.delivaryUser.feature.authentication.verifyphone.data.repository.remote.VerifyPhoneRemoteDataSource
import com.hyperdesign.delivaryUser.feature.authentication.verifyphone.domain.interactors.VerifyPhoneUseCase
import com.hyperdesign.delivaryUser.feature.authentication.verifyphone.domain.repository.IVerifyPhoneRepository
import com.hyperdesign.delivaryUser.feature.authentication.verifyphone.domain.repository.remote.IVerifyPhoneRemoteDataSource
import com.hyperdesign.delivaryUser.feature.authentication.verifyphone.ui.viewmodel.VerifyPhoneViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val verifyPhoneModule = module {
    singleOf(::VerifyPhoneRepository) bind IVerifyPhoneRepository::class
    singleOf(::VerifyPhoneRemoteDataSource) bind IVerifyPhoneRemoteDataSource::class
    factoryOf(::VerifyPhoneUseCase)
    viewModelOf(::VerifyPhoneViewModel)
}