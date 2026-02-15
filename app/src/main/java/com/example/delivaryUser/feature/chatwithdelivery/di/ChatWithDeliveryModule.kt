package com.example.delivaryUser.feature.chatwithdelivery.di

import com.example.delivaryUser.feature.chatwithdelivery.data.repository.ChatWithDeliveryRepository
import com.example.delivaryUser.feature.chatwithdelivery.data.repository.remote.ChatWithDeliveryDataSource
import com.example.delivaryUser.feature.chatwithdelivery.domain.interactors.GetUserChatUseCase
import com.example.delivaryUser.feature.chatwithdelivery.domain.interactors.SendMessageUseCase
import com.example.delivaryUser.feature.chatwithdelivery.domain.repository.IChatWithDeliveryRepository
import com.example.delivaryUser.feature.chatwithdelivery.domain.repository.remote.IChatWithDeliveryDataSource
import com.example.delivaryUser.feature.chatwithdelivery.ui.viewmodel.ChatWithDeliveryViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val chatWithDeliveryModule = module {
    singleOf(::ChatWithDeliveryDataSource) bind IChatWithDeliveryDataSource::class
    singleOf(::ChatWithDeliveryRepository) bind IChatWithDeliveryRepository::class
    factoryOf(::GetUserChatUseCase)
    factoryOf(::SendMessageUseCase)
    viewModelOf(::ChatWithDeliveryViewModel)

}