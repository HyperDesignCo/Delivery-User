package com.example.delivaryUser.service.user.domain.interactors

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.service.user.domain.model.User
import com.example.delivaryUser.service.user.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(private val userRepository: IUserRepository) : BaseUseCase<Flow<Resource<User>>, Unit>() {
    override suspend fun invoke(body: Unit): Flow<Resource<User>> = flowExecute {
        userRepository.getUser()
    }
}