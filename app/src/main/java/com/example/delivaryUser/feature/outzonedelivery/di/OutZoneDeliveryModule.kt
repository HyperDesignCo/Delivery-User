package com.example.delivaryUser.feature.outzonedelivery.di


import com.example.delivaryUser.feature.outzonedelivery.data.repository.OutZoneDeliveryRepository
import com.example.delivaryUser.feature.outzonedelivery.data.repository.remote.OutZoneDeliveryDataSource
import com.example.delivaryUser.feature.outzonedelivery.domain.interactors.AddAreaRequestUseCase
import com.example.delivaryUser.feature.outzonedelivery.domain.repository.IOutZoneDeliveryRepository
import com.example.delivaryUser.feature.outzonedelivery.domain.repository.remote.IOutZoneDeliveryDataSource
import com.example.delivaryUser.feature.outzonedelivery.ui.viewmodel.OutZoneDeliveryViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val outZoneDeliveryModule = module {
    singleOf(::OutZoneDeliveryDataSource) bind IOutZoneDeliveryDataSource::class
    singleOf(::OutZoneDeliveryRepository) bind IOutZoneDeliveryRepository::class
    factoryOf(::AddAreaRequestUseCase)
    viewModelOf(::OutZoneDeliveryViewModel)

}