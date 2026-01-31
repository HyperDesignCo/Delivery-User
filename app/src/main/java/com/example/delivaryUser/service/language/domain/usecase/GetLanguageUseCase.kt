package com.example.delivaryUser.service.language.domain.usecase

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.service.language.domain.repository.ILanguageRepository
import kotlinx.coroutines.flow.Flow

class GetLanguageUseCase(private val repository: ILanguageRepository) :
    BaseUseCase<Flow<Resource<String?>>, Unit>() {
    override suspend fun invoke(body: Unit): Flow<Resource<String>> = flowExecute {
        repository.getLanguage()
    }
}