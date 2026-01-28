package com.example.delivaryUser.feature.address.mapview.domain.usecase

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.feature.address.mapview.domain.repository.IMapRepository
import com.example.delivaryUser.service.location.domain.model.Location
import kotlinx.coroutines.flow.Flow

class SaveLocationResponseUseCase(
    private val repository: IMapRepository
): BaseUseCase<Unit, Location>() {

    override suspend fun invoke(body: Location){
        nonFlowExecute {
            repository.saveLocationResponse(body)
        }
    }
}