package com.example.delivaryUser.service.location.domain.interactors

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.service.location.domain.model.Location
import com.example.delivaryUser.service.location.domain.repository.ILocationRepository
import kotlinx.coroutines.flow.Flow

class GetLocationCheckUseCase(
    private val repository: ILocationRepository
):BaseUseCase<Flow<Resource<Location>>, Unit>() {
    override suspend fun invoke(body: Unit): Flow<Resource<Location>> = flowExecute {
        repository.getCheckLocationResponse()
    }
}