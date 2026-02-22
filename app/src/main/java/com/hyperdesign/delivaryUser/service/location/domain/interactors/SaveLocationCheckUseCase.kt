package com.hyperdesign.delivaryUser.service.location.domain.interactors

import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.service.location.domain.ILocationRepository
import com.hyperdesign.delivaryUser.service.location.domain.model.Location
import kotlinx.coroutines.flow.Flow

class SaveLocationCheckUseCase(
    private val repository: ILocationRepository
): BaseUseCase<Flow<Resource<Unit>>, Location>() {
    override suspend fun invoke(body: Location): Flow<Resource<Unit>> =flowExecute {
        repository.saveCheckLocationResponse(body)
    }
}