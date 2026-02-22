package com.example.delivaryUser.feature.orders.orderdetails.di

import com.example.delivaryUser.feature.orders.orderdetails.domain.ineractors.GetOrderDetailsUseCase
import com.example.delivaryUser.feature.orders.orderdetails.ui.viewmodel.OrderDetailsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val orderDetailsModule = module {
    factoryOf(::GetOrderDetailsUseCase)
    viewModelOf(::OrderDetailsViewModel)
}