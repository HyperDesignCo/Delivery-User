package com.hyperdesign.delivaryUser.service.user.domain.interactors

import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.service.user.domain.repository.IUserRepository

class SaveTokenUseCase(private val repository: IUserRepository) : BaseUseCase<Unit, String>() {
    override suspend fun invoke(body: String) {
        repository.saveToken(body)
    }
}