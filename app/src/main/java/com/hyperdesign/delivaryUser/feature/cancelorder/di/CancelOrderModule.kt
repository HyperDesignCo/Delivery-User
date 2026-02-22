package com.hyperdesign.delivaryUser.feature.cancelorder.di

import com.hyperdesign.delivaryUser.feature.cancelorder.data.repoistroy.CancelOrderRepository
import com.hyperdesign.delivaryUser.feature.cancelorder.data.repoistroy.remote.CancelOrderRemoteDataSource
import com.hyperdesign.delivaryUser.feature.cancelorder.domain.interactors.CancelOrderUseCase
import com.hyperdesign.delivaryUser.feature.cancelorder.domain.repository.ICancelOrderRepository
import com.hyperdesign.delivaryUser.feature.cancelorder.domain.repository.remote.ICancelOrderRemoteDataSource
import com.hyperdesign.delivaryUser.feature.cancelorder.ui.viewmodel.CancelOrderViewModel
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