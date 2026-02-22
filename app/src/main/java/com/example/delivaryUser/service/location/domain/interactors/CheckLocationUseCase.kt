package com.example.delivaryUser.service.location.domain.interactors

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.service.location.data.model.request.CheckLocationRequest
import com.example.delivaryUser.service.location.domain.model.CheckLocation
import com.example.delivaryUser.service.location.domain.repository.ILocationRepository
import kotlinx.coroutines.flow.Flow

class CheckLocationUseCase(
    private val repository: ILocationRepository,
) : BaseUseCase<Flow<Resource<CheckLocation>>, CheckLocationRequest>() {
    override suspend fun invoke(body: CheckLocationRequest): Flow<Resource<CheckLocation>> = flowExecute {
            repository.checkLocation(body)
        }
}