package com.example.delivaryUser.feature.authentication.verifyOtp.di

import com.example.delivaryUser.feature.authentication.verifyOtp.data.repository.VerifyOtpRepository
import com.example.delivaryUser.feature.authentication.verifyOtp.data.repository.remote.VerifyOtpRemoteDataSource
import com.example.delivaryUser.feature.authentication.verifyOtp.domain.interactors.ResendCodeUseCase
import com.example.delivaryUser.feature.authentication.verifyOtp.domain.interactors.VerifyOtpUseCase
import com.example.delivaryUser.feature.authentication.verifyOtp.domain.repository.IVerifyOtpRepository
import com.example.delivaryUser.feature.authentication.verifyOtp.domain.repository.remote.IVerifyOtpRemoteDataSource
import com.example.delivaryUser.feature.authentication.verifyOtp.ui.viewmodel.VerifyOtpViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val verifyOtpModule = module {
    singleOf(::VerifyOtpRepository) bind IVerifyOtpRepository::class
    singleOf(::VerifyOtpRemoteDataSource) bind IVerifyOtpRemoteDataSource::class
    factoryOf(::VerifyOtpUseCase)
    factoryOf(::ResendCodeUseCase)
    viewModelOf(::VerifyOtpViewModel)
}