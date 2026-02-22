package com.hyperdesign.delivaryUser.service.language.domain.repository

interface ILanguageRepository {
    suspend fun saveLanguage(language: String)
    suspend fun getLanguage(): String
}