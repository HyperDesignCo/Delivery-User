package com.example.delivaryUser.feature.address.mapview.domain.interactors

import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.feature.address.mapview.domain.repository.IMapRepository
import com.example.delivaryUser.service.location.domain.model.Location

class SaveLocationResponseUseCase(
    private val repository: IMapRepository
): BaseUseCase<Unit, Location>() {
    override suspend fun invoke(body: Location){
        nonFlowExecute {
            repository.saveLocationResponse(body)
        }
    }
}