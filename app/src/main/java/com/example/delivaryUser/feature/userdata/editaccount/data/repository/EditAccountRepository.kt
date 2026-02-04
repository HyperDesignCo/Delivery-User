package com.example.delivaryUser.feature.userdata.editaccount.data.repository

import com.example.delivaryUser.feature.userdata.editaccount.data.model.request.EditAccountRequest
import com.example.delivaryUser.feature.userdata.editaccount.domain.repository.IEditAccountRepository
import com.example.delivaryUser.feature.userdata.editaccount.domain.repository.remote.IEditAccountRemoteDataSource
import com.example.delivaryUser.service.user.data.dto.UserDto
import com.example.delivaryUser.service.user.data.mapper.UserMapper
import com.example.delivaryUser.service.user.domain.repository.local.IUserLocalDataSource

class EditAccountRepository(private val remote: IEditAccountRemoteDataSource, private val local: IUserLocalDataSource) :
    IEditAccountRepository {
    override suspend fun editAccount(request: EditAccountRequest) {
        val token = local.getToken()
        val result = remote.editAccount(request, token)
        local.saveUser(user = UserMapper.dtoToEntity(result.user ?: UserDto()))
    }
}