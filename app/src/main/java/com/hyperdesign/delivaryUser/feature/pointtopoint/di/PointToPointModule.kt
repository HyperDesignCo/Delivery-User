package com.hyperdesign.delivaryUser.feature.pointtopoint.di

import com.hyperdesign.delivaryUser.feature.pointtopoint.data.repository.PointToPointRepository
import com.hyperdesign.delivaryUser.feature.pointtopoint.data.repository.remote.PointToPointRemoteDataSource
import com.hyperdesign.delivaryUser.feature.pointtopoint.domain.repository.IPointToPointRepository
import com.hyperdesign.delivaryUser.feature.pointtopoint.domain.repository.remote.IPointToPointRemoteDataSource
import com.hyperdesign.delivaryUser.feature.pointtopoint.domain.usecase.AddPointToPointOrderUseCase
import com.hyperdesign.delivaryUser.feature.pointtopoint.domain.usecase.GetDeliveryCostUseCase
import com.hyperdesign.delivaryUser.feature.pointtopoint.domain.usecase.GetOrderPurposesUseCase
import com.hyperdesign.delivaryUser.feature.pointtopoint.ui.viewmodel.PointToPointViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val pointToPointModule = module {
    singleOf(::PointToPointRemoteDataSource) bind IPointToPointRemoteDataSource::class
    singleOf(::PointToPointRepository) bind IPointToPointRepository::class
    factoryOf(::GetOrderPurposesUseCase)
    factoryOf(::GetDeliveryCostUseCase)
    factoryOf(::AddPointToPointOrderUseCase)
    viewModelOf(::PointToPointViewModel)
}