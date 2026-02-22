package com.hyperdesign.delivaryUser.service.user.domain.interactors

import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.service.user.domain.repository.IUserRepository

class GetTokenUseCase(private val repository: IUserRepository) : BaseUseCase<Resource<String>, Unit>() {
    override suspend fun invoke(body: Unit): Resource<String> = nonFlowExecute {
        repository.getToken()
    }
}