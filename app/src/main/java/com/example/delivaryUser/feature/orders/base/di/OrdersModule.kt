package com.example.delivaryUser.feature.orders.base.di

import com.example.delivaryUser.feature.orders.base.data.repository.OrdersRepository
import com.example.delivaryUser.feature.orders.base.data.repository.remote.OrdersRemoteDataSource
import com.example.delivaryUser.feature.orders.base.domain.repository.IOrdersRepository
import com.example.delivaryUser.feature.orders.base.domain.repository.remote.IOrdersRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val ordersModule = module {
    singleOf(::OrdersRepository) bind IOrdersRepository::class
    singleOf(::OrdersRemoteDataSource) bind IOrdersRemoteDataSource::class
}