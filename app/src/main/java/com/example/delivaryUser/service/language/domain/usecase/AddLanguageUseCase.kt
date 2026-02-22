package com.example.delivaryUser.service.language.domain.usecase

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.service.language.domain.repository.ILanguageRepository
import kotlinx.coroutines.flow.Flow

class AddLanguageUseCase(private val repository: ILanguageRepository) :
    BaseUseCase<Flow<Resource<Unit>>, String>() {
    override suspend fun invoke(body: String): Flow<Resource<Unit>> = flowExecute {
        repository.saveLanguage(body)
    }
}