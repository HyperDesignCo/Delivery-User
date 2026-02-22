package com.hyperdesign.delivaryUser.feature.chatwithdelivery.di

import com.hyperdesign.delivaryUser.feature.chatwithdelivery.data.repository.ChatWithDeliveryRepository
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.data.repository.remote.ChatWithDeliveryDataSource
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.domain.interactors.GetUserChatUseCase
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.domain.interactors.SendMessageUseCase
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.domain.repository.IChatWithDeliveryRepository
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.domain.repository.remote.IChatWithDeliveryDataSource
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.ui.viewmodel.ChatWithDeliveryViewModel
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