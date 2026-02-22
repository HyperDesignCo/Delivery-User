package com.example.delivaryUser.feature.fastorder.di

import com.example.delivaryUser.feature.fastorder.data.repository.FastOrderRepository
import com.example.delivaryUser.feature.fastorder.data.repository.remote.FastOrderRemoteDataSource
import com.example.delivaryUser.feature.fastorder.domain.interactors.CreateFastOrderUseCase
import com.example.delivaryUser.feature.fastorder.domain.repository.IFastOrderRepository
import com.example.delivaryUser.feature.fastorder.domain.repository.remote.IFastOrderRemoteDataSource
import com.example.delivaryUser.feature.fastorder.ui.viewmodel.FastOrderViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val fastOrderModule = module {
    singleOf(::FastOrderRepository) bind IFastOrderRepository::class
    singleOf(::FastOrderRemoteDataSource) bind IFastOrderRemoteDataSource::class
    factoryOf(::CreateFastOrderUseCase)
    viewModelOf(::FastOrderViewModel)
}