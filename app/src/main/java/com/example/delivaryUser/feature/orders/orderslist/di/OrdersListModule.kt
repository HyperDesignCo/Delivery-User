package com.example.delivaryUser.feature.orders.orderslist.di

import com.example.delivaryUser.feature.orders.orderslist.domain.interactors.GetOrdersUseCase
import com.example.delivaryUser.feature.orders.orderslist.domain.interactors.RateOrderUseCase
import com.example.delivaryUser.feature.orders.orderslist.ui.viewmodel.OrdersListViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val ordersListModule = module {
    factoryOf(::GetOrdersUseCase)
    factoryOf(::RateOrderUseCase)
    viewModelOf(::OrdersListViewModel)
}