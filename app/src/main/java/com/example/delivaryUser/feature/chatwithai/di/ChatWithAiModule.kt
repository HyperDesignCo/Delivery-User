package com.example.delivaryUser.feature.chatwithai.di

import com.example.delivaryUser.feature.chatwithai.data.repository.ChatWithAiRepository
import com.example.delivaryUser.feature.chatwithai.data.repository.remote.ChatWithAiRemoteDataSource
import com.example.delivaryUser.feature.chatwithai.domain.interactors.ChatWithAiUseCase
import com.example.delivaryUser.feature.chatwithai.domain.repository.IChatWithAiRepository
import com.example.delivaryUser.feature.chatwithai.domain.repository.remote.IChatWithAiRemoteDataSource
import com.example.delivaryUser.feature.chatwithai.ui.viewmodel.ChatWithAiViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val chatWithAiModule = module {
    singleOf(::ChatWithAiRemoteDataSource) bind IChatWithAiRemoteDataSource::class
    singleOf(::ChatWithAiRepository) bind IChatWithAiRepository::class
    factoryOf(::ChatWithAiUseCase)
    viewModelOf(::ChatWithAiViewModel)
}