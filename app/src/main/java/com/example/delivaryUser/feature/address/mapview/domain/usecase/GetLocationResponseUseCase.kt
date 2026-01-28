package com.example.delivaryUser.feature.address.mapview.domain.usecase

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.feature.address.mapview.domain.repository.IMapRepository
import com.example.delivaryUser.service.location.domain.model.Location
import kotlinx.coroutines.flow.Flow

class GetLocationResponseUseCase(
    private val repository: IMapRepository
): BaseUseCase<Flow<Resource<Location?>>,Unit>() {

    override suspend fun invoke(body: Unit): Flow<Resource<Location?>> =flowExecute {
        repository.getSavedLocationResponse()
    }
}