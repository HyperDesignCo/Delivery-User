package com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors

import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.feature.address.mapview.domain.repository.IMapRepository
import com.hyperdesign.delivaryUser.service.location.domain.model.Location
import kotlinx.coroutines.flow.Flow

class GetLocationResponseUseCase(
    private val repository: IMapRepository
): BaseUseCase<Flow<Resource<Location?>>,Unit>() {
    override suspend fun invoke(body: Unit): Flow<Resource<Location?>> =flowExecute {
        repository.getSavedLocationResponse()
    }
}