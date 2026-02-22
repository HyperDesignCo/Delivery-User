package com.example.delivaryUser.service.user.domain.interactors

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.service.user.domain.repository.IUserRepository

class GetTokenUseCase(private val repository: IUserRepository) : BaseUseCase<Resource<String>, Unit>() {
    override suspend fun invoke(body: Unit): Resource<String> = nonFlowExecute {
        repository.getToken()
    }
}