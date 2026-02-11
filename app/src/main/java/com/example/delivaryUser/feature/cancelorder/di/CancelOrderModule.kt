package com.example.delivaryUser.feature.cancelorder.di

import com.example.delivaryUser.feature.cancelorder.data.repoistroy.CancelOrderRepository
import com.example.delivaryUser.feature.cancelorder.data.repoistroy.remote.CancelOrderRemoteDataSource
import com.example.delivaryUser.feature.cancelorder.domain.interactors.CancelOrderUseCase
import com.example.delivaryUser.feature.cancelorder.domain.repository.ICancelOrderRepository
import com.example.delivaryUser.feature.cancelorder.domain.repository.remote.ICancelOrderRemoteDataSource
import com.example.delivaryUser.feature.cancelorder.ui.viewmodel.CancelOrderViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val cancelOrderModule = module {
    singleOf(::CancelOrderRemoteDataSource) bind ICancelOrderRemoteDataSource::class
    singleOf(::CancelOrderRepository) bind ICancelOrderRepository::class
    factoryOf(::CancelOrderUseCase)
    viewModelOf(::CancelOrderViewModel)
}