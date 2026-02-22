package com.hyperdesign.delivaryUser.service.location.domain.interactors

import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.service.location.data.model.request.CheckLocationRequest
import com.hyperdesign.delivaryUser.service.location.domain.ILocationRepository
import com.hyperdesign.delivaryUser.service.location.domain.model.CheckLocation
import kotlinx.coroutines.flow.Flow

class CheckLocationUseCase(
    private val repository: ILocationRepository,
) : BaseUseCase<Flow<Resource<CheckLocation>>, CheckLocationRequest>() {
    override suspend fun invoke(body: CheckLocationRequest): Flow<Resource<CheckLocation>> = flowExecute {
        repository.checkLocation(body)
    }
}