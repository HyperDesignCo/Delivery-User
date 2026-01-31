package com.example.delivaryUser.service.user.domain.interactors

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.service.user.domain.repository.IUserRepository

class SaveIsAuthenticatedUseCase(private val repository: IUserRepository) :
    BaseUseCase<Resource<Unit>, Boolean>() {
    override suspend fun invoke(body: Boolean) = nonFlowExecute {
        repository.saveIsAuthenticated(body)
    }
}