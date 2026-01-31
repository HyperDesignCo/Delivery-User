package com.example.delivaryUser.service.language.data.repository

import com.example.delivaryUser.service.language.domain.repository.ILanguageRepository
import com.example.delivaryUser.service.language.domain.repository.local.ILanguageLocalDataSource

class LanguageRepository(private val local: ILanguageLocalDataSource) : ILanguageRepository {
    override suspend fun saveLanguage(language: String) =
        local.saveLanguage(language)

    override suspend fun getLanguage(): String = local.getLanguage()
}