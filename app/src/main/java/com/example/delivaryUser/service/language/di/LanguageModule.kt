package com.example.delivaryUser.service.language.di

import com.example.delivaryUser.service.language.data.repository.LanguageRepository
import com.example.delivaryUser.service.language.data.repository.local.LanguageLocalDataSource
import com.example.delivaryUser.service.language.domain.repository.ILanguageRepository
import com.example.delivaryUser.service.language.domain.repository.local.ILanguageLocalDataSource
import com.example.delivaryUser.service.language.domain.usecase.AddLanguageUseCase
import com.example.delivaryUser.service.language.domain.usecase.GetLanguageUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val languageModule = module {
    singleOf(::LanguageRepository) bind ILanguageRepository::class
    singleOf(::LanguageLocalDataSource) bind ILanguageLocalDataSource::class
    factoryOf(::AddLanguageUseCase)
    factoryOf(::GetLanguageUseCase)
}