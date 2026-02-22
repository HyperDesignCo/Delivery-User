package com.hyperdesign.delivaryUser.service.language.di

import com.hyperdesign.delivaryUser.service.language.data.repository.LanguageRepository
import com.hyperdesign.delivaryUser.service.language.data.repository.local.LanguageLocalDataSource
import com.hyperdesign.delivaryUser.service.language.domain.repository.ILanguageRepository
import com.hyperdesign.delivaryUser.service.language.domain.repository.local.ILanguageLocalDataSource
import com.hyperdesign.delivaryUser.service.language.domain.usecase.AddLanguageUseCase
import com.hyperdesign.delivaryUser.service.language.domain.usecase.GetLanguageUseCase
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