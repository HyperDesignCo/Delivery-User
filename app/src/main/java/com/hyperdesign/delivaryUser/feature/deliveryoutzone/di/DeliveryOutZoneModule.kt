package com.hyperdesign.delivaryUser.feature.deliveryoutzone.di


import com.hyperdesign.delivaryUser.feature.deliveryoutzone.data.repository.DeliveryOutZoneRepository
import com.hyperdesign.delivaryUser.feature.deliveryoutzone.data.repository.remote.DeliveryOutZoneDataSource
import com.hyperdesign.delivaryUser.feature.deliveryoutzone.domain.interactors.AddAreaUseCase
import com.hyperdesign.delivaryUser.feature.deliveryoutzone.domain.repository.IDeliveryOutZoneRepository
import com.hyperdesign.delivaryUser.feature.deliveryoutzone.domain.repository.remote.IDeliveryOutZoneDataSource
import com.hyperdesign.delivaryUser.feature.deliveryoutzone.ui.viewmodel.DeliveryOutZoneViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val deliveryOutZoneModule = module {
    singleOf(::DeliveryOutZoneDataSource) bind IDeliveryOutZoneDataSource::class
    singleOf(::DeliveryOutZoneRepository) bind IDeliveryOutZoneRepository::class
    factoryOf(::AddAreaUseCase)
    viewModelOf(::DeliveryOutZoneViewModel)
}