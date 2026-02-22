package com.hyperdesign.delivaryUser.feature.authentication.forgetpassword.di

import com.hyperdesign.delivaryUser.feature.authentication.forgetpassword.data.repository.ForgetPasswordRepository
import com.hyperdesign.delivaryUser.feature.authentication.forgetpassword.data.repository.remote.ForgetPasswordRemoteDataSource
import com.hyperdesign.delivaryUser.feature.authentication.forgetpassword.domain.interactors.ForgetPasswordUseCase
import com.hyperdesign.delivaryUser.feature.authentication.forgetpassword.domain.repository.IForgetPasswordRepository
import com.hyperdesign.delivaryUser.feature.authentication.forgetpassword.domain.repository.remote.IForgetPasswordRemoteDataSource
import com.hyperdesign.delivaryUser.feature.authentication.forgetpassword.ui.viewmodel.ForgetPasswordViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val forgetPasswordModule = module {
    singleOf(::ForgetPasswordRepository) bind IForgetPasswordRepository::class
    singleOf(::ForgetPasswordRemoteDataSource) bind IForgetPasswordRemoteDataSource::class
    factoryOf(::ForgetPasswordUseCase)
    factoryOf(::ForgetPasswordViewModel)
}