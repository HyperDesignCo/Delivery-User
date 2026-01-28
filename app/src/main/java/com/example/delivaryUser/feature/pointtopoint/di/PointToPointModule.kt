package com.example.delivaryUser.feature.pointtopoint.di

import com.example.delivaryUser.feature.pointtopoint.data.repository.PointToPointRepository
import com.example.delivaryUser.feature.pointtopoint.data.repository.remote.PointToPointRemoteDataSource
import com.example.delivaryUser.feature.pointtopoint.domain.repository.IPointToPointRepository
import com.example.delivaryUser.feature.pointtopoint.domain.repository.remote.IPointToPointRemoteDataSource
import com.example.delivaryUser.feature.pointtopoint.domain.usecase.AddPointToPointOrderUseCase
import com.example.delivaryUser.feature.pointtopoint.domain.usecase.GetDeliveryCostUseCase
import com.example.delivaryUser.feature.pointtopoint.domain.usecase.GetOrderPurposesUseCase
import com.example.delivaryUser.feature.pointtopoint.ui.viewmodel.PointToPointViewModel
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