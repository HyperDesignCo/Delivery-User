package com.hyperdesign.delivaryUser.feature.orders.orderdetails.di

import com.hyperdesign.delivaryUser.feature.orders.orderdetails.domain.ineractors.GetOrderDetailsUseCase
import com.hyperdesign.delivaryUser.feature.orders.orderdetails.ui.viewmodel.OrderDetailsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val orderDetailsModule = module {
    factoryOf(::GetOrderDetailsUseCase)
    viewModelOf(::OrderDetailsViewModel)
}