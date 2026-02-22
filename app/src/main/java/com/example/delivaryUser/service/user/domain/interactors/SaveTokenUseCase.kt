package com.example.delivaryUser.service.user.domain.interactors

import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.service.user.domain.repository.IUserRepository

class SaveTokenUseCase(private val repository: IUserRepository) : BaseUseCase<Unit, String>() {
    override suspend fun invoke(body: String) {
        repository.saveToken(body)
    }
}