package com.hyperdesign.delivaryUser.service.location.domain.interactors

import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.service.location.domain.ILocationRepository
import com.hyperdesign.delivaryUser.service.location.domain.model.Location
import kotlinx.coroutines.flow.Flow

class GetLocationCheckUseCase(
    private val repository: ILocationRepository,
) : BaseUseCase<Flow<Resource<Location>>, Unit>() {
    override suspend fun invoke(body: Unit): Flow<Resource<Location>> = flowExecute {
        repository.getCheckLocationResponse()
    }
}