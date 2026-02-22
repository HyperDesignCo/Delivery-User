package com.hyperdesign.delivaryUser.service.user.domain.interactors

import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.service.user.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class GetIsAuthenticatedUseCase(private val repository: IUserRepository) :
    BaseUseCase<Flow<Resource<Boolean>>, Unit>() {
    override suspend fun invoke(body: Unit): Flow<Resource<Boolean>> = flowExecute {
        repository.getIsAuthenticated()
    }
}