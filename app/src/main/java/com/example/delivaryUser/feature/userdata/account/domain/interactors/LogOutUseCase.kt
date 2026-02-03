package com.example.delivaryUser.feature.userdata.account.domain.interactors

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.service.address.domain.repository.IAddressRepository
import com.example.delivaryUser.service.user.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class LogOutUseCase(private val userRepository: IUserRepository, private val addressRepository: IAddressRepository) :
    BaseUseCase<Flow<Resource<Unit>>, Unit>() {
    override suspend fun invoke(body: Unit): Flow<Resource<Unit>> = flowExecute {
        userRepository.deleteUser()
        userRepository.deleteToken()
        userRepository.deleteRememberMe()
        userRepository.deleteIsAuthenticated()
        addressRepository.deleteSenderAddress()
        addressRepository.deleteRecipientAddress()
    }
}