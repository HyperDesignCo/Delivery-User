package com.hyperdesign.delivaryUser.service.language.domain.usecase

import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.service.language.domain.repository.ILanguageRepository
import kotlinx.coroutines.flow.Flow

class GetLanguageUseCase(private val repository: ILanguageRepository) :
    BaseUseCase<Flow<Resource<String>>, Unit>() {
    override suspend fun invoke(body: Unit): Flow<Resource<String>> = flowExecute {
        repository.getLanguage()
    }
}