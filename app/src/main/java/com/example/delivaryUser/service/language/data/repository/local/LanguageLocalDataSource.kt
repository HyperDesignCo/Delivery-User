package com.example.delivaryUser.service.language.data.repository.local

import com.example.delivaryUser.common.data.repository.local.LocalDataSourceEnum
import com.example.delivaryUser.common.domain.local.ILocalDataSourceProvider
import com.example.delivaryUser.service.language.domain.repository.local.ILanguageLocalDataSource

class LanguageLocalDataSource(private val provider: ILocalDataSourceProvider) : ILanguageLocalDataSource {
    override suspend fun saveLanguage(language: String) = provider.save(
        LocalDataSourceEnum.LANGUAGE,
        language,
        String::class.java
    )

    override suspend fun getLanguage(): String = provider.read(
        LocalDataSourceEnum.LANGUAGE,
        "en",
        String::class.java
    )
}