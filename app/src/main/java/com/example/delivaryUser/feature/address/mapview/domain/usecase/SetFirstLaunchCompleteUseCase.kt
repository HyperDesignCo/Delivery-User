package com.example.delivaryUser.feature.address.mapview.domain.usecase

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.feature.address.mapview.domain.repository.IMapRepository
import kotlinx.coroutines.flow.Flow

class SetFirstLaunchCompleteUseCase(
    private val repository: IMapRepository
) : BaseUseCase<Flow<Resource<Unit>>, Unit>() {

    override suspend fun invoke(body: Unit): Flow<Resource<Unit>> = flowExecute {
        repository.setFirstLaunchComplete()
    }
}