package com.hyperdesign.delivaryUser.service.user.domain.interactors

import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.service.user.domain.model.User
import com.hyperdesign.delivaryUser.service.user.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(private val userRepository: IUserRepository) : BaseUseCase<Flow<Resource<User>>, Unit>() {
    override suspend fun invoke(body: Unit): Flow<Resource<User>> = flowExecute {
        userRepository.getUser()
    }
}