package com.hyperdesign.delivaryUser.service.language.data.repository.local

import com.hyperdesign.delivaryUser.common.data.repository.local.LocalDataSourceEnum
import com.hyperdesign.delivaryUser.common.domain.local.ILocalDataSourceProvider
import com.hyperdesign.delivaryUser.service.language.domain.repository.local.ILanguageLocalDataSource

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