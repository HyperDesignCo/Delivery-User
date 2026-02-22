package com.example.delivaryUser.service.location.domain.interactors

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.service.location.domain.model.Location
import com.example.delivaryUser.service.location.domain.repository.ILocationRepository
import kotlinx.coroutines.flow.Flow

class SaveLocationCheckUseCase(
    private val repository: ILocationRepository
): BaseUseCase<Flow<Resource<Unit>>, Location>() {
    override suspend fun invoke(body: Location): Flow<Resource<Unit>> =flowExecute {
        repository.saveCheckLocationResponse(body)
    }

}