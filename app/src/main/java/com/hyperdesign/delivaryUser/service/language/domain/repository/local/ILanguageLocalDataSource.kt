package com.hyperdesign.delivaryUser.service.language.domain.repository.local

interface ILanguageLocalDataSource {
    suspend fun saveLanguage(language: String)
    suspend fun getLanguage(): String
}