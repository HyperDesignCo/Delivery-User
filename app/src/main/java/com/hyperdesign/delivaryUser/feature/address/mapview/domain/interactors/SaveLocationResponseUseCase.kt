package com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors

import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.feature.address.mapview.domain.repository.IMapRepository
import com.hyperdesign.delivaryUser.service.location.domain.model.Location

class SaveLocationResponseUseCase(
    private val repository: IMapRepository
): BaseUseCase<Unit, Location>() {
    override suspend fun invoke(body: Location){
        nonFlowExecute {
            repository.saveLocationResponse(body)
        }
    }
}