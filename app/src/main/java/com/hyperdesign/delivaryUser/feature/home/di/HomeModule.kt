package com.hyperdesign.delivaryUser.feature.home.di

import com.hyperdesign.delivaryUser.feature.home.data.repository.HomeRepository
import com.hyperdesign.delivaryUser.feature.home.data.repository.remote.HomeRemoteDataSource
import com.hyperdesign.delivaryUser.feature.home.domain.interactors.GetAdsUseCase
import com.hyperdesign.delivaryUser.feature.home.domain.repository.IHomeRepository
import com.hyperdesign.delivaryUser.feature.home.domain.repository.remote.IHomeRemoteDataSource
import com.hyperdesign.delivaryUser.feature.home.ui.viewmodel.HomeViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val homeModule = module {
    singleOf(::HomeRepository) bind IHomeRepository::class
    singleOf(::HomeRemoteDataSource) bind IHomeRemoteDataSource::class
    factoryOf(::GetAdsUseCase)
    viewModelOf(::HomeViewModel)
}