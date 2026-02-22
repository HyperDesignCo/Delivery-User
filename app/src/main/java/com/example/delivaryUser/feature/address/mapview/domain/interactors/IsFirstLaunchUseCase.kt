package com.example.delivaryUser.feature.address.mapview.domain.interactors

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.feature.address.mapview.domain.repository.IMapRepository
import kotlinx.coroutines.flow.Flow

class IsFirstLaunchUseCase(
    private val repository: IMapRepository
) : BaseUseCase<Flow<Resource<Boolean>>, Unit>() {
    override suspend fun invoke(body: Unit): Flow<Resource<Boolean>> = flowExecute {
        repository.isFirstLaunch()
    }
}